package andersen.dev.tickets.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import andersen.dev.tickets.exception.UsertNotFoundException;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@SpringBootTest(classes = { UserService.class })
@RequiredArgsConstructor
public class UserServiceTest {
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	final Ticket goodTicket = new Ticket().setTicketName("AAA");
	final User goodUser = new User().setUsername("TEST1").setBpassword("TEST1");
	final User nullUser = null;
	final User goodUserWithTickets = new User().setUsername("TEST2").setBpassword("TEST2")
			.setTickets(Collections.singletonList(goodTicket));
	final User badUser = new User().setUsername("awd");

	@Test
	void testАddUser_succesful() {
		when(userRepository.save(goodUser)).thenReturn(goodUser);
		assertEquals(userService.addUser(goodUser), goodUser);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void testАddUser_NullUserIsPassed() {
		User user = null;
		when(userRepository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
		verify(userRepository, times(1)).save(any());
	}

	/**
	 * Test scenario where validation fails on DB side (non-unique value, etc)
	 */
	@Test
	void testАddUser_invalidUserConstraintViolation() {
		when(userRepository.save(badUser)).thenThrow(new DataIntegrityViolationException(""));
		assertThrows(DataIntegrityViolationException.class, () -> userService.addUser(badUser));
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void testGetUserByIdWithTickets_Success() {
		when(userRepository.findUserWithTicketsById(anyInt())).thenReturn(Optional.of(goodUserWithTickets));
		assertEquals(goodUserWithTickets, userService.getUserByIdWithTickets(1));
		verify(userRepository, times(1)).findUserWithTicketsById(anyInt());
	}

	@Test
	void testGetUserByIdWithTickets_NotFound() {
		when(userRepository.findUserWithTicketsById(anyInt())).thenReturn(Optional.empty());
		assertThrows(UsertNotFoundException.class, () -> userService.getUserByIdWithTickets(1));
		verify(userRepository, times(1)).findUserWithTicketsById(anyInt());
	}

	@Test
	void testGetUserByIdWithoutTickets_Success() {
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(goodUser));
		userService.getUserByIdWithoutTickets(1);
		verify(userRepository, times(1)).findById(anyInt());
	}

	@Test
	void testGetUserByIdWithoutTickets_NotFound() {
		when(userRepository.findById(anyInt())).thenThrow(new UsertNotFoundException());
		assertThrows(UsertNotFoundException.class, () -> userService.getUserByIdWithoutTickets(1));
		verify(userRepository, times(1)).findById(anyInt());
	}

	@Test
	void testDeleteUser_success() {
		when(userRepository.findUserWithTicketsById(anyInt())).thenReturn(Optional.of(goodUser));
		doNothing().when(userRepository).delete(goodUser);
		userService.deleteUser(1);
		verify(userRepository, times(1)).findUserWithTicketsById(anyInt());
		verify(userRepository, times(1)).delete(any(User.class));
	}

	@Test
	void testDeleteUser_userNotFound() {
		when(userRepository.findUserWithTicketsById(anyInt())).thenReturn(Optional.empty());
		assertThrows(UsertNotFoundException.class, () -> userService.deleteUser(1));
		verify(userRepository, times(1)).findUserWithTicketsById(anyInt());
		verify(userRepository, never()).delete(any(User.class));
	}

	/**
	 * Test when, for example, delete fails because of cascade issues
	 */
	@Test
	void testDeleteUser_DeleteException() {
		when(userRepository.findUserWithTicketsById(anyInt())).thenReturn(Optional.of(goodUserWithTickets));
		doThrow(new DataIntegrityViolationException("")).when(userRepository).delete(goodUserWithTickets);
		assertThrows(DataIntegrityViolationException.class, () -> userService.deleteUser(1));
		verify(userRepository, times(1)).findUserWithTicketsById(anyInt());
		verify(userRepository, times(1)).delete(goodUserWithTickets);
	}

	@Test
	void testLoadUserByUsername_succesful() {
		when(userRepository.findByUsername(goodUserWithTickets.getUsername()))
				.thenReturn(Optional.of(goodUserWithTickets));
		UserDetails userDetails = userService.loadUserByUsername(goodUserWithTickets.getUsername());
		assertNotNull(userDetails);
		assertEquals(goodUserWithTickets.getUsername(), userDetails.getUsername());
		verify(userRepository).findByUsername(anyString());
	}

	@Test
	void testLoadUserByUsername_NotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("QWERTY"));
		verify(userRepository).findByUsername(anyString());
	}
}
