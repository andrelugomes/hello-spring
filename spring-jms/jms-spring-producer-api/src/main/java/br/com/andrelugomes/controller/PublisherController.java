package br.com.andrelugomes.controller;

import br.com.andrelugomes.producer.TopicPublisher;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/topic", description = "Serviço REST de envio de mensagens em tópicos")
@RestController
@RequestMapping(value = "/topic")
public class PublisherController {

	@Autowired
	private TopicPublisher publisher;

    @ApiOperation(value = "Envio de texto simples", httpMethod = "POST",
            notes ="Envio de texto simples para o tópico : <b>text.topic</b>")
    @ApiImplicitParam(name = "texto", value = "texto simples", paramType = "query",
            dataType = "String", defaultValue = "blank_message", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
	@RequestMapping(value = "/text", method=RequestMethod.POST)
    public String textSender(@RequestParam(value="texto", defaultValue="blank_message") String texto) {
		publisher.sendText(texto);
        return "Message sent!";
    }
}
