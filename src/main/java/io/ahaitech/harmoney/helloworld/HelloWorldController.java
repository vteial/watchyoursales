package io.ahaitech.harmoney.helloworld;

import groovy.text.Template;
import io.ahaitech.harmoney.dto.MailDto;
import io.ahaitech.harmoney.service.MailService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

@RestController
@RequestMapping("/helloworld")
@Slf4j
public class HelloWorldController {

	@Resource
	RestTemplate restTemplate;

	@Resource
	Template displayTemplate;

	@Value("http://#{T(java.net.InetAddress).localHost.canonicalHostName}:${server.port:${management.port:8080}}")
	private String hostAndPort;

	@Value("${management.context-path:/}")
	private String mgtCtxPath;

	@Value("${spring.application.name:un-named}")
	private String name;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public @ResponseBody String info() {
		return hostAndPort + mgtCtxPath + " - " + name;
	}

	@RequestMapping(value = "/echoBack", method = RequestMethod.GET)
	public @ResponseBody String echoBack() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println(Strings.repeat("-", 120));
		try {
			String s = this.hostAndPort + "/helloworld/info";
			pw.println("Url : " + s);
			pw.println(Strings.repeat("-", 120));
			ResponseEntity<String> response = restTemplate.getForEntity(s,
					String.class);
			pw.println("Headers :");
			HttpHeaders httpHeaders = response.getHeaders();
			for (Entry<String, List<String>> entry : httpHeaders.entrySet()) {
				pw.println(entry);
			}
			pw.println(httpHeaders);
			pw.println(Strings.repeat("-", 120));
			pw.println("Body : ");
			pw.println(response.getBody());
		} catch (Throwable t) {
			pw.println(Throwables.getStackTraceAsString(t));
		}
		pw.println(Strings.repeat("-", 120));

		Map<String, String> binding = new HashMap<String, String>();
		binding.put("contents", sw.toString());
		this.displayTemplate.make(binding);

		return this.displayTemplate.make(binding).toString();
	}

	@Resource
	MailService mailSenderService;

	@RequestMapping("/testMail")
	public @ResponseBody String testMail() {
		String s = "TestMail successfully sent...";

		try {
			MailDto mailDto = this.createMailDto();
			this.mailSenderService.sendAsync(mailDto);
		} catch (Throwable t) {
			s = Throwables.getStackTraceAsString(t);

		}

		return s;
	}

	@Resource(name = "alertChannel")
	MessageChannel alertChannel;

	@RequestMapping("/testMailViaAlertChannel")
	public @ResponseBody String testMailViaAlertChannel() {
		String s = "TestAlert successfully sent...";

		MessageBuilder<String> mb = MessageBuilder
				.withPayload("This is test mail...");
		Message<String> message = mb.build();
		boolean flag = this.alertChannel.send(message);

		log.info("Alert Channel Status : {}", flag);

		return s;
	}

	@Resource(name = "mailChannel")
	MessageChannel mailChannel;

	@RequestMapping("/testMailViaMailChannel")
	public @ResponseBody String testMailViaMailChannel() {
		String s = "TestMail successfully sent...";

		MailDto mailDto = this.createMailDto();
		MessageBuilder<MailDto> mb = MessageBuilder.withPayload(mailDto);
		Message<MailDto> message = mb.build();
		boolean flag = this.mailChannel.send(message);

		log.info("Mail Channel Status : {}", flag);

		return s;
	}

	MailDto createMailDto() {
		MailDto mailDto = new MailDto();

		mailDto.setFromAddress("mail-deamon@localhost.com");
		mailDto.setToAddress("vteial@gmail.com");
		mailDto.setSubject("Test Mail");
		mailDto.setBody("This is test message...");

		return mailDto;
	}
}