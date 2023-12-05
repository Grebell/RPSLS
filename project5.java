public class project5 {
    public static void main(String[] args) {
        if (args[0] != null && (args[0].equals("help") || args[0].equals("?") || args[0].equals("-h"))) {
            //help
            System.out.println("\nPlay the Rock, Paper, Scissors, Lizard, Spock game using the provided values. The first two (2) input values indicate the initial shapes selected by Sheldon and Leonard, respectively, to play in the first round. The third input value is the number of rounds (1 or more) to play.\n" +
                    "\n" +
                    "Examples:\n" +
                    "\n" +
                    "./project5 SPOCK ROCK 4\n" +
                    "./project5 ROCK LIZARD 3\n" +
                    "./project5 PAPER LIZARD 5\n" +
                    "./project5 SPOCK SPOCK 2");
            System.exit(0);
        }
        //initialize stuff
        assert args[0] != null;
        Hand sheldon = Hand.valueOf(args[0].toUpperCase());
        Hand leonard = Hand.valueOf(args[1].toUpperCase());
        int rounds = Integer.parseInt(args[2]);
        int shewins = 0;
        int leowins = 0;
        int ties = 0;
        int round = 1;
        int [][] winmatrix = {   {0,2,1,2,1},
                                 {1,0,2,1,2},
                                 {2,1,0,2,1},
                                 {1,2,1,0,2},
                                 {2,1,2,1,0}};
        winstate state = null;
        //lets goooooooo
        System.out.println("\nRock, Paper, Scissors, Lizard, Spock\n");
        System.out.println("Sheldon's First Shape: " + sheldon);
        System.out.println("Leonard's First Shape: " + leonard);
        while (round <= rounds) {
            //logic
            switch (winmatrix[sheldon.ordinal()][leonard.ordinal()])
                {
                    case 0:
                        ties++;
                        state = winstate.TIE;
                        break;
                    case 1:
                        shewins++;
                        state = winstate.SHE;
                        break;
                    case 2:
                        leowins++;
                        state = winstate.LEO;
                        break;

            }
            //end logic
            //strategics
            leonard = LeonardStrat(leonard, sheldon, state);
            sheldon = SheldonStrat(sheldon, state);
            round++;
        }
        //result output shenanigans, why play if you can't see who wins^^
        if (shewins > leowins) {
            System.out.println("\nSheldon Wins!");
        }
        else if (leowins > shewins) {
            System.out.println("\nLeonard Wins!");
        }
        else {
            System.out.println("\nTie Game!");
        }
        if (shewins == leowins) {
            System.out.println("Sheldon and Leonard each won " + shewins + " game(s), and they tied " + ties + " game(s).");
        }
        else {
            System.out.println("Sheldon won " + shewins + " game(s), Leonard won " + leowins + " game(s), and tied " + ties + " game(s).");
        }
    }
// ooo yay method time
    //lets strategize
    public static Hand SheldonStrat(Hand current, winstate state) {
        Hand next = null;
        if (current == Hand.SPOCK) {
            if (state == winstate.SHE) {
                next = Hand.ROCK;
            }
            else if (state == winstate.LEO) {
                next = Hand.PAPER;
            }
            else if (state == winstate.TIE) {
                next = Hand.LIZARD;
            }
        }
        else {
            next = Hand.SPOCK;
        }
        return next;
    }

    public static Hand LeonardStrat(Hand Lcurrent, Hand Scurrent, winstate state) {
        Hand next = null;
        if (state == winstate.LEO) {
            next = Lcurrent;
        }
        else if (state == winstate.SHE) {
            next = getHand(Scurrent);
        }
        else if (state == winstate.TIE) {
            next = getHand(Lcurrent);
        }
        return next;
    }

    private static Hand getHand(Hand current) {
        Hand next = null;
        if (current == Hand.ROCK) {
            next = Hand.PAPER; //better
            //next = Hand.SPOCK; //lesser
        }
        else if (current == Hand.PAPER) {
            next = Hand.SCISSOR; //better
            //next = Hand.LIZARD; //lesser
        }
        else if (current == Hand.SCISSOR) {
            next = Hand.SPOCK; //better
            //next = Hand.ROCK; //lesser
        }
        else if (current == Hand.LIZARD) {
            next = Hand.ROCK; //better
            //next = Hand.SCISSOR; //lesser
        }
        else if (current == Hand.SPOCK) {
            next = Hand.LIZARD; //better
            //next = Hand.PAPER; //lesser
        }
        return next;
    }
//enums!
    public enum Hand {
        ROCK, PAPER, SCISSOR, SPOCK, LIZARD
    }

    public enum winstate {
        SHE, LEO, TIE
    }

}
