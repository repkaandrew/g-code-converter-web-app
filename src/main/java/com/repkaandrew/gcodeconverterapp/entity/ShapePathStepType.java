package com.repkaandrew.gcodeconverterapp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShapePathStepType {
  LINE(1), CW(2), CCW(3);

  private final int id;

  @JsonValue
  public int getId() {
    return id;
  }

  public boolean isArc() {
    return this == CW || this == CCW;
  }

  @JsonCreator
  public static ShapePathStepType from(int id) {
    return Arrays.stream(values())
        .filter(value -> Objects.equals(value.id, id))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Illegal id: " + id));
  }
}
