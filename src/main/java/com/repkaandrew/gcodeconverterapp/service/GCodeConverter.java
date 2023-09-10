package com.repkaandrew.gcodeconverterapp.service;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repkaandrew.gcodeconverterapp.entity.BoardPath;
import com.repkaandrew.gcodeconverterapp.entity.ShapePathStep;
import java.text.MessageFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GCodeConverter {

  private final ObjectMapper mapper;

  /**
   * Generates GCode for provided shape path steps
   *
   * @param pathAsJson - path steps to be interpreted into GCode frames
   * @return string with GCode
   */
  public String pathToGCode(String pathAsJson) throws JsonProcessingException {

    if (pathAsJson.contains("tip") && pathAsJson.contains("bottom")) {
      final BoardPath path = mapper.readValue(pathAsJson, BoardPath.class);

      return stepsToGCode(path.getFull());
    }

    final var reader = mapper.readerFor(new TypeReference<List<ShapePathStep>>() {
    });

    return stepsToGCode(reader.readValue(pathAsJson));
  }

  private String stepsToGCode(List<ShapePathStep> steps) {
    return steps.stream()
        .map(step -> {
          final var stepType = step.getType();
          final var rPart = step.getRadius() != null && stepType.isArc()
              ? "R" + step.getRadius()
              : "";
          return MessageFormat.format(
              "G{0} X{1, number, #.#####} Y{2, number, #.#####} {3}",
              stepType.getId(),
              step.getX(),
              step.getY(),
              rPart
          );
        })
        .collect(joining("\n"));
  }
}
