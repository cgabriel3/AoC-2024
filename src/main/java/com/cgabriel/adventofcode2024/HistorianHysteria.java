package com.cgabriel.adventofcode2024;

import com.cgabriel.adventofcode2024.vo.HistorianHysteriaVO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistorianHysteria extends AbstractInputService {
  public final static String DAY = "1";

  @Override
  public String getDay() {
    return DAY;
  }

  public HistorianHysteriaVO getVO() {
    String input = getInput();
    List<Integer> leftNumbers = new ArrayList<>();
    List<Integer> rightNumbers = new ArrayList<>();
    String[] lines = input.split("\n");
    for (String line : lines) {
      String[] parts = line.trim().split("\\s+"); // Split by whitespace
      if (parts.length == 2) {
        leftNumbers.add(Integer.parseInt(parts[0])); // Add left number
        rightNumbers.add(Integer.parseInt(parts[1])); // Add right number
      }
    }
    HistorianHysteriaVO historianHysteriaVO = new HistorianHysteriaVO();
    historianHysteriaVO.leftNumbers = leftNumbers;
    historianHysteriaVO.rightNumbers = rightNumbers;
    return historianHysteriaVO;
  }

  public int getOneStarSolution() {
    HistorianHysteriaVO historianHysteriaVO = getVO();
    Collections.sort(historianHysteriaVO.leftNumbers);
    Collections.sort(historianHysteriaVO.rightNumbers);

    int sum = 0;
    for (int i = 0; i < historianHysteriaVO.leftNumbers.size(); i++) {
      sum += Math.abs(historianHysteriaVO.leftNumbers.get(i) - historianHysteriaVO.rightNumbers.get(i));
    }
    return sum;
  }

  public int getTwoStarSolution() {
    HistorianHysteriaVO historianHysteriaVO = getVO();
    int sum = 0;
    Map<Integer, Integer> numberFrequency = new HashMap<>();
    for (int i = 0; i < historianHysteriaVO.rightNumbers.size(); i++) {
      int number = historianHysteriaVO.rightNumbers.get(i);
      if (numberFrequency.containsKey(number)) {
        numberFrequency.put(number, numberFrequency.get(number) + 1);
        continue;
      }
      numberFrequency.put(number, numberFrequency.getOrDefault(number, 0) + 1);
    }

    for (int i = 0; i < historianHysteriaVO.leftNumbers.size(); i++) {
      int number = historianHysteriaVO.leftNumbers.get(i);
      if (numberFrequency.containsKey(number)) {
        sum += number * numberFrequency.get(number);
      }
    }
    return sum;
  }
}
