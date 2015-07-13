package com.vincent.tictactoe.core;

/**
 * A Position on a tic-tac-toe board. Since these Positions are used for a
 * 2-D board, each position has a `getX` and `getY` method.
 */
public enum Position {
    TOP_LEFT {
        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }
    },
    TOP_CENTER {
        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 1;
        }

    },
    TOP_RIGHT {
        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 2;
        }

    },
    CENTER_LEFT {
        @Override
        public int getX() {
            return 1;
        }

        @Override
        public int getY() {
            return 0;
        }
    },
    CENTER {
        @Override
        public int getX() {
            return 1;
        }

        @Override
        public int getY() {
            return 1;
        }
    },
    CENTER_RIGHT {
        @Override
        public int getX() {
            return 1;
        }

        @Override
        public int getY() {
            return 2;
        }
    },
    BOTTOM_LEFT {
        @Override
        public int getX() {
            return 2;
        }

        @Override
        public int getY() {
            return 0;
        }
    },
    BOTTOM_CENTER {
        @Override
        public int getX() {
            return 2;
        }

        @Override
        public int getY() {
            return 1;
        }
    },
    BOTTOM_RIGHT {
        @Override
        public int getX() {
            return 2;
        }

        @Override
        public int getY() {
            return 2;
        }
    };

    public abstract int getX();
    public abstract int getY();
}
