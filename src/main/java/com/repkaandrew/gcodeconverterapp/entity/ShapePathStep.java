package com.repkaandrew.gcodeconverterapp.entity;

import static com.repkaandrew.gcodeconverterapp.entity.ShapePathStepType.CCW;
import static com.repkaandrew.gcodeconverterapp.entity.ShapePathStepType.CW;
import static com.repkaandrew.gcodeconverterapp.entity.ShapePathStepType.LINE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class ShapePathStep {

  private final ShapePathStepType type;
  private final double x;
  private final double y;
  private final Double radius;

  public static ShapePathStep line(double x, double y) {
    return ShapePathStep.builder()
        .x(x)
        .y(y)
        .radius(null)
        .type(LINE)
        .build();
  }

  public static ShapePathStep arcCW(double x, double y, double r) {
    return ShapePathStep.builder()
        .x(x)
        .y(y)
        .radius(r)
        .type(CW)
        .build();
  }

  public static ShapePathStep arcCCW(double x, double y, double r) {
    return ShapePathStep.builder()
        .x(x)
        .y(y)
        .radius(r)
        .type(CCW)
        .build();
  }
}

