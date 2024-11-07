package andersen.dev.tickets.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import lombok.RequiredArgsConstructor;

@Component
public class TicketParser {
	private final ObjectMapper mapper = new ObjectMapper();
	private final String filepath;

	TicketParser(@Value("${json.ticket.filepath}") String filepath) {
		this.filepath = filepath;
	}

	public List<Ticket> fromJsonToTicket() throws IOException {
		List<Ticket> tickets = new ArrayList<>();

		mapper.findAndRegisterModules();
		JsonNode nodes;
		try {
			nodes = mapper.readTree(new File(filepath));
		} catch (IOException ex) {
			throw new IOException(filepath + " is not a valid directory");
		}

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
