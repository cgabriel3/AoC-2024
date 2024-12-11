package com.cgabriel.adventofcode2024;

import org.springframework.stereotype.Service;

@Service
public class CeresSearch extends AbstractInputService {
  public final static String DAY = "4";

  @Override
  public String getDay() {
    return DAY;
  }

  public int getOneStarSolution() {
    getInput();
    return 0;
  }
}
