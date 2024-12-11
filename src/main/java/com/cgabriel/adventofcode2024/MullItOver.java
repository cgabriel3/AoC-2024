package com.cgabriel.adventofcode2024;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MullItOver extends AbstractInputService {
  public final static String DAY = "3";

  @Override
  public String getDay() {
    return DAY;
  }

  public int getOneStarSolution() {
    List<String> lines = getMulList(getInput());
    return getMulTotal(lines);
  }

  public List<String> getMulList(String input) {
    String regex = "mul\\((\\d+),\\s*(\\d+)\\)";
    // Create a Pattern object
    Pattern pattern = Pattern.compile(regex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(input);

    // List to store the matched substrings
    List<String> matches = new ArrayList<>();
    while (matcher.find()) {
      matches.add(matcher.group());
    }
    return matches;
  }

  public List<String> getCommandList(String input) {
    String regex = "mul\\(\\d+,\\s*\\d+\\)|do\\(\\)|don't\\(\\)";
    // Create a Pattern object
    Pattern pattern = Pattern.compile(regex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(input);

    // List to store the matched substrings
    List<String> matches = new ArrayList<>();
    while (matcher.find()) {
      matches.add(matcher.group());
    }
    return matches;
  }

  public int getTwoStarSolution() {
    String input = getInput();

    List<String> commandList = getCommandList(input);
    List<String> mulList = new ArrayList<>();
    boolean doFlag = true;
    for (String line : commandList) {
      if (line.equals("do()")) {
        doFlag = true;
      } else if (line.equals("don't()")) {
        doFlag = false;
      } else if (doFlag) {
        mulList.add(line);
      }
    }
    return getMulTotal(mulList);
  }

  private int getMulTotal(List<String> mulList) {
    int total = 0;
    for (String line : mulList) {
      String cleanedStr = line.replaceAll("[^0-9,]", "");
      String[] nums = cleanedStr.split(",");
      int num1 = Integer.parseInt(nums[0]);
      int num2 = Integer.parseInt(nums[1]);
      total += num1 * num2;
    }
    return total;
  }
}
