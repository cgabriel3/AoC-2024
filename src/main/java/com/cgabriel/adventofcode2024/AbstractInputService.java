package com.cgabriel.adventofcode2024;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractInputService {
  @Autowired
  protected AdventOfCodeService adventOfCodeService;

  public abstract String getDay();

  public String getLink() {
    return "https://adventofcode.com/2024/day/" + getDay() + "/input";
  }

  public String getInput() {
    return adventOfCodeService.fetchInput(getLink());
  }
}
