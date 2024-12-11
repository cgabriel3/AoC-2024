package com.cgabriel.adventofcode2024;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedNosedReports extends AbstractInputService {
  public final static String DAY = "2";

  @Override
  public String getDay() {
    return DAY;
  }

  public List<List<Integer>> getLevel() {
    String input = getInput();
    String[] lines = input.split("\n");
    List<List<Integer>> levelList = new ArrayList<>();
    for (String line : lines) {
      String[] levels = line.split(" ");
      List<Integer> level = new ArrayList<>();
      for (String l : levels) {
        level.add(Integer.parseInt(l));
      }
      levelList.add(level);
    }
    return levelList;
  }

  public int getOneStarSolution() {
    List<List<Integer>> levels = getLevel();
    int total = 0;
    for (List<Integer> level : levels) {
      if (checkSafe(level)) {
        total++;
      }
    }
    return total;
  }

  public int getTwoStarSolution() {
    List<List<Integer>> levels = getLevel();
    int total = 0;
    for (List<Integer> level : levels) {
      if (checkSafe(level)) {
        total++;
      } else {
        if (problemDampener(level)) {
          total++;
        }
      }
    }
    return total;
  }

  public boolean checkSafe(List<Integer> level) {
    boolean increasing = true;
    boolean decreasing = true;
    for (int i = 0; i < level.size() - 1; i++) {
      if (level.get(i) > level.get(i + 1)) {
        increasing = false;
      } else if (level.get(i) < level.get(i + 1)) {
        decreasing = false;
      }

      if (!getDiff(level.get(i), level.get(i + 1))) {
        return false;
      }
    }
    return increasing || decreasing;
  }

  public boolean problemDampener(List<Integer> level) {
    for (int i = 0; i < level.size(); i++) {
      List<Integer> tempLevel = new ArrayList<>(level);
      tempLevel.remove(i);
      if (checkSafe(tempLevel)) {
        return true;
      }
    }
    return false;
  }

  public boolean getDiff(int a, int b) {
    int diff = (a > b) ? a - b : b - a;
    return diff > 3 || diff < 1;
  }
}
