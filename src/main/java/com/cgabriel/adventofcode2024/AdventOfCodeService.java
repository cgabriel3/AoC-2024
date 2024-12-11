package com.cgabriel.adventofcode2024;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AdventOfCodeService {
  private static final String SESSION_COOKIE = "53616c7465645f5ff5a66f317661f31050d82508dd5be756e2577ff0abdc58b9fbf6ad997b83347bc2a9f37bf28b25ae83551789a29e36e1cb8cf399cd17dde8"; // Replace with your session cookie

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
