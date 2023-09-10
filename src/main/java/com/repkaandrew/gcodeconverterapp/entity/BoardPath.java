package com.repkaandrew.gcodeconverterapp.entity;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BoardPath {

  private final List<ShapePathStep> tip;

  private final List<ShapePathStep> bottom;

  public BoardPath() {
    tip = emptyList();
    bottom = emptyList();
  }

  @JsonIgnore
  public List<ShapePathStep> getFull() {
    return isEmpty(bottom)
        ? emptyList()
        : ImmutableList.<ShapePathStep>builder()
        .add(bottom.get(0))
        .addAll(tip)
        .add(bottom.get(bottom.size() - 1))
        .add(bottom.get(0))
        .build();
  }
}

