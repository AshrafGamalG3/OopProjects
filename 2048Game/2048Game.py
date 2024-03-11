import tkinter as tk
import random


class Game(tk.Frame):
    def __init__(self):
        tk.Frame.__init__(self)
        self.grid()
        self.master.title('2048')
        self.game_over_flag = False
        self.main_grid = tk.Frame(self, bg='lightgray', bd=3, width=400, height=400)
        self.main_grid.grid(pady=(80, 0))
        self.make_GUI()
        self.start_game()

        self.master.bind("<Left>", self.left)
        self.master.bind("<Right>", self.right)
        self.master.bind("<Up>", self.up)
        self.master.bind("<Down>", self.down)

        self.after(1000, self.computer_play)

        self.mainloop()

    def make_GUI(self):
        self.cells = []
        for i in range(4):
            row = []
            for j in range(4):
                cell_frame = tk.Frame(self.main_grid, bg='white', width=100, height=100)
                cell_frame.grid(row=i, column=j, padx=5, pady=5)
                cell_number = tk.Label(self.main_grid, bg='white')
                cell_number.grid(row=i, column=j)
                cell_data = {"frame": cell_frame, "number": cell_number}
                row.append(cell_data)
            self.cells.append(row)

        score_frame = tk.Frame(self)
        score_frame.place(relx=0.5, y=40, anchor="center")
        tk.Label(score_frame, text="score", font=('Arial', 14)).grid(row=0)
        self.score_label = tk.Label(score_frame, text="0", font=('Arial', 18))
        self.score_label.grid(row=1)

    def start_game(self):
        self.matrix = [[0] * 4 for _ in range(4)]
        self.Add_New_NUMBER()
        self.score = 0

    def Search_Of_Not_Zero_And_Add_To_New_Matrix(self, board):
        new_matrix = [[0] * 4 for _ in range(4)]
        for i in range(4):
            fill_position = 0
            for j in range(4):
                if board[i][j] != 0:
                    new_matrix[i][fill_position] = board[i][j]
                    fill_position += 1
        return new_matrix

    def sum(self, board):
        for i in range(4):
            for j in range(3):
                if board[i][j] != 0 and board[i][j] == board[i][j + 1]:
                    board[i][j] *= 2
                    board[i][j + 1] = 0
                    self.score += board[i][j]

    def reverse(self, board):
        new_matrix = []
        for i in range(4):
            new_matrix.append([])
            for j in range(4):
                new_matrix[i].append(board[i][3 - j])
        return new_matrix  # [[]]

    def transpose(self, board):
        new_matrix = [[0] * 4 for _ in range(4)]
        for i in range(4):
            for j in range(4):
                new_matrix[i][j] = board[j][i]
        return new_matrix

    def Add_New_NUMBER(self):
        empty_cells = [(i, j) for i in range(4) for j in range(4) if self.matrix[i][j] == 0]
        if empty_cells:
            row, col = random.choice(empty_cells)
            self.matrix[row][col] = random.choice([8, 8])

    def update_GUI(self):
        for i in range(4):
            for j in range(4):
                cell_value = self.matrix[i][j]
                if cell_value == 0:
                    self.cells[i][j]["frame"].configure(bg='white')
                    self.cells[i][j]["number"].configure(bg='white', text="")
                else:
                    self.cells[i][j]["frame"].configure(bg='lightgray')
                    self.cells[i][j]["number"].configure(bg='lightgray', text=str(cell_value))
        self.score_label.configure(text=self.score)
        self.update_idletasks()

    def left(self, event):
        if not self.game_over_flag:
            self.matrix = self.reverse(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.sum(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.Add_New_NUMBER()
            self.update_GUI()
            self.game_over()
            self.after(1000, self.computer_play)

    def right(self, event):
        if not self.game_over_flag:
            self.matrix = self.reverse(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.sum(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.matrix = self.reverse(self.matrix)
            self.Add_New_NUMBER()
            self.update_GUI()
            self.game_over()
            self.after(1000, self.computer_play)

    def up(self, event):
        if not self.game_over_flag:
            self.matrix = self.transpose(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.sum(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.matrix = self.transpose(self.matrix)
            self.Add_New_NUMBER()
            self.update_GUI()
            self.game_over()
            self.after(1000, self.computer_play)

    def down(self, event):
        if not self.game_over_flag:
            self.matrix = self.transpose(self.matrix)
            self.matrix = self.reverse(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.sum(self.matrix)
            self.matrix = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(self.matrix)
            self.matrix = self.reverse(self.matrix)
            self.matrix = self.transpose(self.matrix)
            self.Add_New_NUMBER()
            self.update_GUI()
            self.game_over()
            self.after(1000, self.computer_play)

    def horizontal_move_exists(self, board):
        for i in range(4):
            for j in range(3):
                if board[i][j] == board[i][j + 1]:
                    return True
        return False

    def vertical_move_exists(self, board):
        for i in range(3):
            for j in range(4):
                if board[i][j] == board[i + 1][j]:
                    return True
        return False

    def game_over(self):
        if any(2048 in row for row in self.matrix):
            self.show_game_result("You win!", 'lightgreen')
            self.game_over_flag = True

        elif not any(0 in row for row in self.matrix) and not self.horizontal_move_exists(
                self.matrix) and not self.vertical_move_exists(self.matrix):
            self.show_game_result("Game over!", 'lightcoral')
            self.game_over_flag = True

    def show_game_result(self, result_text, bg_color):
        game_over_frame = tk.Frame(self.main_grid, borderwidth=2)
        game_over_frame.place(relx=0.5, rely=0.5, anchor="center")
        tk.Label(
            game_over_frame,
            text=result_text,
            bg=bg_color,
            fg='black',
            font=('Arial', 16)
        ).pack()

    def computer_play(self):
        _, best_move = self.minimax(self.matrix, depth=2, maximizing_player=True, alpha=float('-inf'),
                                    beta=float('inf'))
        if best_move == 'left':
            self.left(None)
        elif best_move == 'right':
            self.right(None)
        elif best_move == 'up':
            self.up(None)
        elif best_move == 'down':
            self.down(None)

    def minimax(self, board, depth, maximizing_player, alpha, beta):
        if depth == 0 or self.is_terminal_node(board):
            return self.evaluate_board(board), None

        if maximizing_player:
            max_eval = float('-inf')
            best_move = None
            for move in ['right', 'down', 'up', 'left']:
                new_board = self.simulate_move(board, move)
                eval, _ = self.minimax(new_board, depth - 1, False, alpha, beta)
                if eval > max_eval:
                    max_eval = eval
                    best_move = move
                alpha = max(alpha, eval)
                if beta <= alpha:
                    break
            return max_eval, best_move
        else:
            min_eval = float('inf')
            best_move = None
            for move in ['left', 'up', 'right', 'down']:
                new_board = self.simulate_move(board, move)
                eval, _ = self.minimax(new_board, depth - 1, True, alpha, beta)
                if eval < min_eval:
                    min_eval = eval
                    best_move = move
                beta = min(beta, eval)
                if beta <= alpha:
                    break
            return min_eval, best_move

    def is_terminal_node(self, board):
        return not any(0 in row for row in board) and not self.horizontal_move_exists(
            board) and not self.vertical_move_exists(board)

    def evaluate_board(self, board):
        return sum(sum(row) for row in board) + len([(i, j) for i in range(4) for j in range(4) if board[i][j] == 0])

    def simulate_move(self, board, move):
        new_board = [row.copy() for row in board]
        if move == 'left':
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            self.sum(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
        elif move == 'right':
            new_board = self.reverse(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            self.sum(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            new_board = self.reverse(new_board)
        elif move == 'up':
            new_board = self.transpose(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            self.sum(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            new_board = self.transpose(new_board)
        elif move == 'down':
            new_board = self.transpose(new_board)
            new_board = self.reverse(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            self.sum(new_board)
            new_board = self.Search_Of_Not_Zero_And_Add_To_New_Matrix(new_board)
            new_board = self.reverse(new_board)
            new_board = self.transpose(new_board)
        return new_board


def main():
    Game()


if __name__ == "__main__":
    main()
