# Prisoner's Dilemma Game Simulation

This project simulates a game inspired by the **Iterated Prisoner's Dilemma**, a classic game theory problem. Two players participate in a series of rounds where they can choose to cooperate or defect based on predefined strategies. Each choice has a specific payoff, and players aim to maximize their score. This simulation is designed in Java and showcases various strategies to observe how different approaches perform against each other.

## Motivation

The Prisoner's Dilemma is a fundamental problem in game theory that explores the conflict between individual interest and mutual benefit. In real-world scenarios, such as economics, politics, and even daily human interactions, individuals and groups often face decisions where cooperation might yield mutual benefits, but defection (acting in self-interest) could result in higher rewards if others cooperate.

This project aims to simulate various strategies in the Prisoner's Dilemma to analyze how different approaches (like always cooperating, always defecting, tit-for-tat, and random choices) fare against each other over multiple rounds. By examining these outcomes, we gain insights into the dynamics of trust, reciprocity, and competition, and understand which strategies are more sustainable or advantageous in long-term interactions. This simulation provides a simplified environment to observe the consequences of these strategies and their potential applications beyond theoretical models.

## Features

- **Multiple Strategies**: Players can adopt different strategies:
  - **All_C**: Always cooperate.
  - **All_D**: Always defect.
  - **TFT**: Tit-for-tat - cooperate initially, then mimic the opponent's last move.
  - **RAND**: Random - randomly choose between cooperation and defection.
  - **STFT**: Suspicious tit-for-tat - defect initially, then mimic the opponent’s last move.
  
- **Dynamic Gameplay**: Players interact and make moves based on their strategies and their opponent’s last move.

- **Score Calculation**: Scores are calculated based on the standard payoff matrix:
  - Both cooperate: +3 points each
  - Both defect: +1 point each
  - One defects, one cooperates: +5 for the defector, +0 for the cooperator

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor to run the Java program
