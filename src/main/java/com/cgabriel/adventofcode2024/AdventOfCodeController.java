package com.cgabriel.adventofcode2024;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdventOfCodeController {
  @Autowired
  private HistorianHysteria historianHysteria;
  @Autowired
  private RedNosedReports redNosedReports;
  @Autowired
  private MullItOver mullItOver;
  @Autowired
  private CeresSearch ceresSearch;

  @GetMapping("/historianHysteria/one-star")
  public int getHistorianHysteriaOneStarSolution() {
    return historianHysteria.getOneStarSolution();
  }

  @GetMapping("/historianHysteria/two-star")
  public int getHistorianHysteriaTwoStarSolution() {
    return historianHysteria.getTwoStarSolution();
  }

  @GetMapping("/redNosedReports/one-star")
  public int getRedNosedReportsOneStarSolution() {
    return redNosedReports.getOneStarSolution();
  }

  @GetMapping("/redNosedReports/two-star")
  public int getRedNosedReportsTwoStarSolution() {
    return redNosedReports.getTwoStarSolution();
  }

  @GetMapping("/mullItOver/one-star")
  public int getMullItOverOneStarSolution() {
    return mullItOver.getOneStarSolution();
  }

  @GetMapping("/mullItOver/two-star")
  public int getMullItOverTwoStarSolution() {
    return mullItOver.getTwoStarSolution();
  }

  @GetMapping("/ceresSearch/one-star")
  public int getCeresSearchOneStarSolution() {
    return ceresSearch.getOneStarSolution();
  }
}
