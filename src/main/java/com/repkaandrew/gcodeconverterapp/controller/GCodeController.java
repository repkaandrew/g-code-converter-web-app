package com.repkaandrew.gcodeconverterapp.controller;

import static java.util.Collections.emptyList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.repkaandrew.gcodeconverterapp.entity.PathInput;
import com.repkaandrew.gcodeconverterapp.service.GCodeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GCodeController {

  private final GCodeConverter converter;

  @GetMapping
  public String getGCode(Model model) {
    final var gCode = emptyList();
    model.addAttribute("gCode", gCode);
    model.addAttribute("pathInput", new PathInput());
    return "g-code";
  }

  @PostMapping
  public String postGCode(
      @ModelAttribute("pathInput") PathInput pathInput,
      Model model
  ) {
    String gCode;
    try {
      gCode = converter.pathToGCode(pathInput.getSteps());
    } catch (JsonProcessingException e) {
      gCode = "Wrong json provided. It can be only list of steps or entire board path";
    }

    model.addAttribute("gCode", gCode.split("\n"));

    return "g-code";
  }
}
