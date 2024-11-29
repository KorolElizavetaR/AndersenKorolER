package andersen.dev.tickets.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.dto.UserDTO;
import andersen.dev.tickets.model.User;

@Component
public class UserMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO getUserDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}
