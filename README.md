Tetris ReadME

      This is a simple Tetris game built with multiple classes, including Board, BoardSquare, Tetris, 
      Constants, Game, and a PaneOrganizer class. 
        - Each class uses wrapper techniques so that no class knows too much about the others, keeping the 
          design clean. 
        
        - The game has keyboard controls: arrow keys to move and rotate, spacebar to drop pieces, and P to pause. 
        
        - Internally, it uses 2D arrays, loops, conditional statements, and helper methods to manage the 
          gameplay and logic.

Design Choices:

        Each Tetris piece is made of four squares, so I created moveRight, moveLeft, and moveDown methods in 
        BoardSquare and looped through the squares in Tetris to move them individually.
        
        The board background is a 22x12 grid made with a 2D array of BoardSquares, and I set the border 
        squares to grey using a boolean isBorder.
        
        To clear lines, I loop through each row, check if it’s full with a helper method, and then shift 
        all rows above down by copying their colors.
        
        Rotation uses a helper method to check if the piece can rotate without overlapping or going out 
        of bounds, and then the rotate method updates the squares’ positions if it’s allowed.
        
        The game uses loops, 2D arrays, helper methods, and organized classes to handle movement, rotation, 
        and line clearing while keeping each class focused on its own task.
