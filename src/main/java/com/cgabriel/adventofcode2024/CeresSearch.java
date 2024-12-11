package com.cgabriel.adventofcode2024;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CeresSearch extends AbstractInputService {
  public final static String DAY = "4";
  public final static String XMAS = "XMAS";
  public final static String START_OF_WORD = "X";
  public final static String X_MAS_INDICATOR = "A";
  public final static String MAS = "MAS";
  public final static String SAM = "SAM";

  @Override
  public String getDay() {
    return DAY;
  }

  public String testInput() {
    return "MMMSXXMASM\n" +
        "MSAMXMSMSA\n" +
        "AMXSXMAAMM\n" +
        "MSAMASMSMX\n" +
        "XMASAMXAMM\n" +
        "XXAMMXXAMA\n" +
        "SMSMSASXSS\n" +
        "SAXAMASAAA\n" +
        "MAMMMXMMMM\n" +
        "MXMXAXMASX";
  }

  public int getOneStarSolution() {
    String xmasInput = getInput();
    List<String> xmasInputList = Arrays.stream(xmasInput.split("\n")).toList();
    int total = 0;
    log.info("xmasInputList Size: {}, length: {}", xmasInputList.size(), xmasInputList.get(0).length());
    for (int i = 0; i < xmasInputList.size(); i++) {
      for (int j = 0; j < xmasInputList.get(i).length(); j++) {
        if (xmasInputList.get(i).charAt(j) == START_OF_WORD.charAt(0)) {
          try {
            if (checkHorizontal(i, j, xmasInputList)) {
              total++;
            }

            if (checkHorizontalReverse(i, j, xmasInputList)) {
              total++;
            }

            if (checkVertical(i, j, xmasInputList)) {
              total++;
            }

            if (checkVerticalReverse(i, j, xmasInputList)) {
              total++;
            }

            if (checkDiagonalLeftUp(i, j, xmasInputList)) {
              total++;
            }

            if (checkDiagonalLeftDown(i, j, xmasInputList)) {
              total++;
            }

            if (checkDiagonalRightUp(i, j, xmasInputList)) {
              total++;
            }

            if (checkDiagonalRightDown(i, j, xmasInputList)) {
              total++;
            }
          } catch (Exception e) {
            log.error("Error out of bounds at i: " + i + " j: " + j, e);
          }
        }
      }
    }
    return total;
  }

  public boolean checkHorizontal(int i, int j, List<String> xmasInputList) {
    if (j > xmasInputList.get(i).length() - 4) {
      return false;
    }
    StringBuilder vertical = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      vertical.append(xmasInputList.get(i).charAt(j + k));
    }
    return compareStringToXMAS(vertical.toString());
  }

  public boolean checkHorizontalReverse(int i, int j, List<String> xmasInputList) {
    if (j < 3) {
      return false;
    }
    StringBuilder vertical = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      vertical.append(xmasInputList.get(i).charAt(j - k));
    }
    return compareStringToXMAS(vertical.toString());
  }

  public boolean checkVertical(int i, int j, List<String> xmasInputList) {
    if (i > xmasInputList.size() - 4) {
      return false;
    }
    StringBuilder vertical = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      vertical.append(xmasInputList.get(i + k).charAt(j));
    }
    return compareStringToXMAS(vertical.toString());
  }

  public boolean checkVerticalReverse(int i, int j, List<String> xmasInputList) {
    if (i < 3) {
      return false;
    }
    StringBuilder vertical = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      vertical.append(xmasInputList.get(i - k).charAt(j));
    }
    return compareStringToXMAS(vertical.toString());
  }

  public boolean checkDiagonalLeftUp(int i, int j, List<String> xmasInputList) {
    if (i < 3 || j < 3) {
      return false;
    }
    StringBuilder diagonal = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      diagonal.append(xmasInputList.get(i - k).charAt(j - k));
    }
    return compareStringToXMAS(diagonal.toString());
  }

  public boolean checkDiagonalLeftDown(int i, int j, List<String> xmasInputList) {
    if (i > xmasInputList.size() - 4 || j < 3) {
      return false;
    }
    StringBuilder diagonal = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      diagonal.append(xmasInputList.get(i + k).charAt(j - k));
    }
    return compareStringToXMAS(diagonal.toString());
  }

  public boolean checkDiagonalRightUp(int i, int j, List<String> xmasInputList) {
    if (i < 3 || j > xmasInputList.get(i).length() - 4) {
      return false;
    }
    StringBuilder diagonal = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      diagonal.append(xmasInputList.get(i - k).charAt(j + k));
    }
    return compareStringToXMAS(diagonal.toString());
  }

  public boolean checkDiagonalRightDown(int i, int j, List<String> xmasInputList) {
    if (i > xmasInputList.size() - 4 || j > xmasInputList.get(i).length() - 4) {
      return false;
    }
    StringBuilder diagonal = new StringBuilder();
    for (int k = 0; k < 4; k++) {
      diagonal.append(xmasInputList.get(i + k).charAt(j + k));
    }
    return compareStringToXMAS(diagonal.toString());
  }

  public int getTwoStarSolution() {
    String xmasInput = getInput();
    List<String> xmasInputList = Arrays.stream(xmasInput.split("\n")).toList();
    int total = 0;
    for (int i = 0; i < xmasInputList.size(); i++) {
      for (int j = 0; j < xmasInputList.get(i).length(); j++) {
        if (xmasInputList.get(i).charAt(j) == X_MAS_INDICATOR.charAt(0)) {
          if (checkXMASPattern(i, j, xmasInputList)) {
            total++;
          }
        }
      }
    }
    return total;
  }

  public boolean checkXMASPattern(int i, int j, List<String> xmasInputList) {
    if (i < 1 || j < 1 || i > xmasInputList.size() - 2 || j > xmasInputList.get(i).length() - 2) {
      return false;
    }

    StringBuilder left = new StringBuilder();
    left.append(xmasInputList.get(i - 1).charAt(j - 1));
    left.append(xmasInputList.get(i).charAt(j));
    left.append(xmasInputList.get(i + 1).charAt(j + 1));

    StringBuilder right = new StringBuilder();
    right.append(xmasInputList.get(i - 1).charAt(j + 1));
    right.append(xmasInputList.get(i).charAt(j));
    right.append(xmasInputList.get(i + 1).charAt(j - 1));
    return compareStringToMas(left.toString()) && compareStringToMas(right.toString());
  }

  public boolean compareStringToMas(String str) {
    return str.equals(MAS) || str.equals(SAM);
  }

  public boolean compareStringToXMAS(String str) {
    return str.equals(XMAS);
  }
}
