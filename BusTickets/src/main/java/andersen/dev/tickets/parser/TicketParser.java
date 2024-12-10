package andersen.dev.tickets.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketParser {
	private final ObjectMapper mapper;
	@Value("${json.ticket.filepath}")
	private String filepath;

	public List<Ticket> fromJsonToTicket() throws IOException {
		List<Ticket> tickets = new ArrayList<>();

		mapper.findAndRegisterModules();
		JsonNode nodes = mapper.readTree(new File(filepath));

		for (JsonNode element : nodes) {
			String ticketName = element.path("ticketName").asText();
			String ticketTypeString = element.path("ticketType").asText();
			TicketType ticketType;
			try {
				ticketType = TicketType.valueOf(ticketTypeString);
			} catch (IllegalArgumentException ex) {
				ticketType = null;
			}
			String dateAsText = element.path("startDate").asText();
			LocalDate startDate = (dateAsText == "null" || dateAsText.isBlank()) ? null
					: LocalDate.parse(dateAsText, DateTimeFormatter.ISO_LOCAL_DATE);
			int price = element.path("price").asInt();
			tickets.add(new Ticket(ticketName, ticketType, startDate, price));
		}
		return tickets;
	}
}
