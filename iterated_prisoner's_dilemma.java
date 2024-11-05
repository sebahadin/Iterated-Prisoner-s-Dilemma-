import java.util.Random;

class Strategy {
    private int currentStrategy; // Current strategy of the player
    private int consecutiveMove; // Tracks consecutive moves under the same strategy
    private static final int COOPERATE = 0, DEFECT = 1;
    private static final int ALL_C = 1, ALL_D = 2, TFT = 3, RAND = 4, STFT = 5;

    public Strategy() { // Constructor
        currentStrategy = ALL_C;
        consecutiveMove = 0;
    }

    // Method to set strategy for a player
    public void setStrategy(int strategy) {
        if (strategy >= 1 && strategy <= 5) {
            currentStrategy = strategy;
            consecutiveMove = 0;
        } else {
            System.err.println("Invalid strategy. Choose between 1 and 5.");
        }
    }

    // Method to determine if the player should cooperate or defect based on strategy
    public int cooperateOrDefect(int opponentLastMove) {
        switch (currentStrategy) {
            case ALL_C:
                consecutiveMove++;
                return COOPERATE;
            case ALL_D:
                consecutiveMove++;
                return DEFECT;
            case TFT:
                if (consecutiveMove == 0) {
                    consecutiveMove++;
                    return COOPERATE;
                } else {
                    return opponentLastMove;
                }
            case RAND:
                Random rand = new Random();
                consecutiveMove++;
                return rand.nextInt(2); // Randomly return 0 (cooperate) or 1 (defect)
            case STFT:
                if (consecutiveMove == 0) {
                    consecutiveMove++;
                    return DEFECT;
                } else {
                    return opponentLastMove;
                }
            default:
                System.err.println("Invalid move");
                return -1;
        }
    }
}

class Player {
    private static int playerCount = 0; // Tracks number of players
    private int id;
    private Strategy strategy;
    private int score;
    private int maxMoves;
    private int numMoves;
    private int[] prevMoves;

    public Player() {
        id = ++playerCount;
        strategy = new Strategy();
        score = 0;
        maxMoves = 0;
        numMoves = 0;
        prevMoves = null;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setMaxMoves(int max) {
        if (maxMoves == 0) {
            maxMoves = max;
            prevMoves = new int[maxMoves];
        } else {
            System.out.println("Max moves already set.");
        }
    }

    public void updateStrategy(int newStrategy) {
        strategy.setStrategy(newStrategy);
    }

    public void makeMove(int opponentLastMove) {
        if (numMoves < maxMoves) {
            int move = strategy.cooperateOrDefect(opponentLastMove);
            prevMoves[numMoves++] = move;
        } else {
            System.out.println("No more moves left for the player.");
        }
    }

    public int getLastMove() {
        return numMoves == 0 ? -1 : prevMoves[numMoves - 1];
    }

    public void calculateScore(Player opponent) {
        if (numMoves == opponent.numMoves) {
            for (int i = 0; i < numMoves; i++) {
                if (prevMoves[i] == COOPERATE && opponent.prevMoves[i] == COOPERATE) {
                    score += 3;
                } else if (prevMoves[i] == DEFECT && opponent.prevMoves[i] == DEFECT) {
                    score += 1;
                } else if (prevMoves[i] == DEFECT && opponent.prevMoves[i] == COOPERATE) {
                    score += 5;
                } // No points for cooperate-defect situation
            }
        } else {
            System.err.println("Both players must make the same number of moves before calculating score.");
        }
    }

    public void printMoves() {
        System.out.println("Moves for Player " + id + ":");
        for (int i = 0; i < numMoves; i++) {
            System.out.println("Move " + (i + 1) + ": " + (prevMoves[i] == 0 ? "Cooperate" : "Defect"));
        }
    }
}

class Game {
    private Player[] players;
    private int maxMoves;
    private int playerCount;
    private boolean scoreCalculated;

    public Game(int maxMoves) {
        this.maxMoves = maxMoves;
        players = new Player[2];
        playerCount = 0;
        scoreCalculated = false;
    }

    public void addPlayer(Player player) {
        if (playerCount < players.length) {
            players[playerCount++] = player;
            player.setMaxMoves(maxMoves);
        } else {
            System.out.println("Cannot add more players.");
        }
    }

    public void play() {
        if (playerCount == 2) {
            for (int i = 0; i < maxMoves; i++) {
                players[0].makeMove(players[1].getLastMove());
                players[1].makeMove(players[0].getLastMove());
            }
            calculateScore();
            reportResults();
        } else {
            System.out.println("Not enough players to start the game.");
        }
    }

    private void calculateScore() {
        players[0].calculateScore(players[1]);
        players[1].calculateScore(players[0]);
        scoreCalculated = true;
    }

    private void reportResults() {
        if (scoreCalculated) {
            for (Player player : players) {
                player.printMoves();
                System.out.println("Score: " + player.getScore());
            }
        } else {
            System.out.println("Score must be calculated to display results.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game(5);
        Player p1 = new Player();
        Player p2 = new Player();

        // Set strategies
        p1.updateStrategy(4); // Random strategy
        p2.updateStrategy(3); // Tit-for-tat strategy

        // Add players and play the game
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.play();
    }
}
