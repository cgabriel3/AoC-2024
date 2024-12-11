package com.cgabriel.adventofcode2024;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AdventOfCodeService {
  private static final String SESSION_COOKIE = "53616c7465645f5f5688e210a584e764d914755292e12f666fe24d47d4da1fed8acdf2e11f329dd47453ed00f490b0f52a4ab0cc96c6df3e9221efc4da9b655f"; // Replace with your session cookie

  public String fetchInput(String questionUrl) {
    StringBuilder content = new StringBuilder();
    try {
      URL url = new URL(questionUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Cookie", "session=" + SESSION_COOKIE);

      int responseCode = connection.getResponseCode();
      if (responseCode == 200) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
          content.append(line).append("\n");
        }
        reader.close();
      } else {
        throw new RuntimeException("Failed to fetch input. Response code: " + responseCode);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error while fetching Advent of Code input: ", e);
    }
    return content.toString();
  }
}
