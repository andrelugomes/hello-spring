package br.com.andrelugomes.controller;

import br.com.andrelugomes.producer.QueueProducer;
import br.com.andrelugomes.producer.message.JsonMessage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "/queue", description = "Serviço REST de envio de mensagens em filas")
@RestController
@RequestMapping(value = "/queue")
public class ProducerController {

	@Autowired
	private QueueProducer queueProducer;

    @ApiOperation(value = "Envio de texto simples", httpMethod = "POST",
            notes ="Envio de texto simples para fila : <b>text.queue</b>")
    @ApiImplicitParam(name = "texto", value = "texto simples", paramType = "query",
            dataType = "String", defaultValue = "blank_message", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
	@RequestMapping(value = "/text", method=RequestMethod.POST)
    public String textSender(@RequestParam(value="texto", defaultValue="blank_message") String texto) {
		queueProducer.sendText(texto);
        return "Message sent!";
    }

    @ApiOperation(value = "Envio de número inteiro", httpMethod = "POST",
            notes ="Envio de número inteiro para fila : <b>number.queue</b>")
    @ApiImplicitParam(name = "number", value = "número inteiro", paramType = "query",
            dataType = "Integer", defaultValue = "123456", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
	@RequestMapping(value = "/number", method=RequestMethod.POST)
    public String numberSender(@RequestParam(value="number", defaultValue="123456") Integer number) {
		queueProducer.sendNumber(number);
        return "Message sent!";
    }

    @ApiOperation(value = "Envio de texto simples agendado com delay", httpMethod = "POST",
            notes ="Envio de texto simples agendado com delay para fila : <b>scheduled.queue</b>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "texto", value = "texto simples", paramType = "query",
                    dataType = "String", defaultValue = "blank_message"),
            @ApiImplicitParam(name = "delay", value = "valor em segundos", paramType = "query",
                    dataType = "Long",  defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
    @RequestMapping(value = "/schedule", method=RequestMethod.POST)
    public String scheduledMessage(@RequestParam(value="texto", defaultValue="blank_message") String text,
                                   @RequestParam(value="delay", defaultValue="1") Long delay) {
        queueProducer.sendScheduledMessage(text, (delay * 1000));
        return "Message scheduled";
    }

    @ApiOperation(value = "Envio de JSON", httpMethod = "POST", notes ="Envio de JSON para fila : <b>json.queue</b>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JsonMessage.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
	@RequestMapping(value = "/json", method=RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<JsonMessage> json(@RequestBody JsonMessage json) {
		queueProducer.sendJsonMessage(json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ApiOperation(value = "Envio de XML", httpMethod = "POST", notes ="Envio de XML para fila : <b>xnl.queue</b><br/><pre>"
            + "&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;\n" + //
            "&lt;pedido&gt;\n" + //
            "  &lt;id&gt; 123 &lt;/id&gt;\n" + //
            "  &lt;detalhes&gt; DETALHES &lt;/detalhes&gt;\n" + //
            "&lt;/pedido&gt;\n</pre>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
    @RequestMapping(value = "/xml", method=RequestMethod.POST, consumes = "application/xml")
    public ResponseEntity<String> xml(@RequestBody String xml) {
        queueProducer.sendXml(xml);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }
}
