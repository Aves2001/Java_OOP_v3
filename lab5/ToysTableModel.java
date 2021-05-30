package lab5;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ToysTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7068483729972103559L;
	private List<Toy> toys;

	public List<Toy> getToys() {
		return toys;
	}

	public ToysTableModel(List<Toy> toys) {
		this.toys = toys;
	}

	public int getRowCount() {
		return toys.size();
	}

	public int getColumnCount() {
		return 6;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			switch (columnIndex) {
			case 0:
				return toys.get(rowIndex).getTypeToys();
			case 1:
				return toys.get(rowIndex).getName();
			case 2:
				return toys.get(rowIndex).getPrice();
			case 3:
				return toys.get(rowIndex).getAgeLimits().getFrom();
			case 4:
				return toys.get(rowIndex).getAgeLimits().getTo();
			case 5:
				return toys.get(rowIndex).getParameter();
			default:
				return "";
			}
		} catch (Exception e) {}
		return null;
	}

	String[] columName = { "Тип", "Назва", "Вартість (коп)", "Вікові межі (мін)", "Вікові межі (макс)", "Параметр" };

	@Override
	public String getColumnName(int column) {
		try {
			return columName[column];
		} catch (Exception e) {
			return "";
		}
	}

	public void removeRow(int selectedRow) {
		if (selectedRow < 0) {
			selectedRow = toys.size() - 1;
		}
		if (selectedRow >= 0) {
			toys.remove(selectedRow);
			fireTableRowsInserted(toys.size() - 1, toys.size() - 1);
		}
	}

	public void editRow(int selectedRow, Toy toy) {
		if (selectedRow >= 0) {
			toys.get(selectedRow).setAll(toy);
			fireTableRowsInserted(selectedRow, selectedRow);
		}
	}

	public void add(Toy toy) {
		toys.add(toy);
		fireTableRowsInserted(toys.size() - 1, toys.size() - 1);
	}

	public void update(List<Toy> toys) {
		this.toys = toys;
		fireTableRowsInserted(0, this.toys.size() - 1);
	}

	public void clear() {
		toys.removeAll(toys);
	}
}