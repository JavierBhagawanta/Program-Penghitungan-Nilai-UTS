package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UTSCalculator extends JFrame {
    private JTextField txtNama, txtNilai1, txtNilai2, txtNilai3, txtHasil;
    private JButton btnHitung, btnReset, btnUpdate, btnDelete, btnUpload;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblImage;
    private BufferedImage uploadedImage;

    public UTSCalculator() {
        setTitle("Calculator Nilai UTS - Enhanced GUI");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER
        JLabel headerLabel = new JLabel("Calculator Nilai UTS", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(70, 130, 180));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Nilai UTS"));
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNama = new JLabel("Nama Mahasiswa:");
        txtNama = new JTextField();

        JLabel lblNilai1 = new JLabel("Nilai UTS 1:");
        txtNilai1 = new JTextField();

        JLabel lblNilai2 = new JLabel("Nilai UTS 2:");
        txtNilai2 = new JTextField();

        JLabel lblNilai3 = new JLabel("Nilai UTS 3:");
        txtNilai3 = new JTextField();

        JLabel lblHasil = new JLabel("Nilai Rata-rata:");
        txtHasil = new JTextField();
        txtHasil.setEditable(false);

        // Buttons
        btnHitung = createButton("Hitung", new Color(60, 179, 113));
        btnReset = createButton("Reset", new Color(255, 69, 0));
        btnUpdate = createButton("Update", new Color(30, 144, 255));
        btnDelete = createButton("Delete", new Color(255, 99, 71));
        btnUpload = createButton("Upload Image", new Color(218, 112, 214));

        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(100, 100));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        addToPanel(inputPanel, gbc, lblNama, 0, 0);
        addToPanel(inputPanel, gbc, txtNama, 1, 0);
        addToPanel(inputPanel, gbc, lblNilai1, 0, 1);
        addToPanel(inputPanel, gbc, txtNilai1, 1, 1);
        addToPanel(inputPanel, gbc, lblNilai2, 0, 2);
        addToPanel(inputPanel, gbc, txtNilai2, 1, 2);
        addToPanel(inputPanel, gbc, lblNilai3, 0, 3);
        addToPanel(inputPanel, gbc, txtNilai3, 1, 3);
        addToPanel(inputPanel, gbc, lblHasil, 0, 4);
        addToPanel(inputPanel, gbc, txtHasil, 1, 4);
        addToPanel(inputPanel, gbc, btnHitung, 0, 5);
        addToPanel(inputPanel, gbc, btnReset, 1, 5);
        addToPanel(inputPanel, gbc, btnUpdate, 0, 6);
        addToPanel(inputPanel, gbc, btnDelete, 1, 6);
        addToPanel(inputPanel, gbc, btnUpload, 0, 7);
        addToPanel(inputPanel, gbc, lblImage, 1, 7);

        add(inputPanel, BorderLayout.WEST);

        // TABLE
        String[] columnNames = {"Nama", "Nilai UTS 1", "Nilai UTS 2", "Nilai UTS 3", "Rata-rata", "Foto"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Custom table cell renderer for images
        table.getColumnModel().getColumn(5).setCellRenderer(new ImageCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // EVENT HANDLERS
        btnHitung.addActionListener(e -> hitungNilai());
        btnReset.addActionListener(e -> resetForm());
        btnDelete.addActionListener(e -> deleteSelectedRow());
        btnUpdate.addActionListener(e -> updateSelectedRow());
        btnUpload.addActionListener(e -> uploadImage());

        setVisible(true);
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                uploadedImage = ImageIO.read(selectedFile);
                ImageIcon icon = new ImageIcon(uploadedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                lblImage.setIcon(icon);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to load image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diupdate!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nama = txtNama.getText();
            double nilai1 = Double.parseDouble(txtNilai1.getText());
            double nilai2 = Double.parseDouble(txtNilai2.getText());
            double nilai3 = Double.parseDouble(txtNilai3.getText());
            double rataRata = (nilai1 + nilai2 + nilai3) / 3;

            // Set row values including image as an ImageIcon
            tableModel.setValueAt(nama, selectedRow, 0);
            tableModel.setValueAt(nilai1, selectedRow, 1);
            tableModel.setValueAt(nilai2, selectedRow, 2);
            tableModel.setValueAt(nilai3, selectedRow, 3);
            tableModel.setValueAt(String.format("%.2f", rataRata), selectedRow, 4);
            if (uploadedImage != null) {
                tableModel.setValueAt(new ImageIcon(uploadedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH)), selectedRow, 5);
            }

            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid, masukkan angka yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetForm() {
        txtNama.setText("");
        txtNilai1.setText("");
        txtNilai2.setText("");
        txtNilai3.setText("");
        txtHasil.setText("");
        lblImage.setIcon(null);
        uploadedImage = null;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void addToPanel(JPanel panel, GridBagConstraints gbc, Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    private void hitungNilai() {
        try {
            String nama = txtNama.getText();

            // Check if 'Nama' contains any numbers
            if (nama.matches(".\\d.")) {  // RegEx to check if name contains any digits
                JOptionPane.showMessageDialog(this, "Nama tidak boleh mengandung angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Proceed if the name is valid (doesn't contain numbers)
            double nilai1 = Double.parseDouble(txtNilai1.getText());
            double nilai2 = Double.parseDouble(txtNilai2.getText());
            double nilai3 = Double.parseDouble(txtNilai3.getText());
            double rataRata = (nilai1 + nilai2 + nilai3) / 3;

            txtHasil.setText(String.format("%.2f", rataRata));
            if (uploadedImage != null) {
                tableModel.addRow(new Object[]{nama, nilai1, nilai2, nilai3, rataRata, new ImageIcon(uploadedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH))});
            } else {
                tableModel.addRow(new Object[]{nama, nilai1, nilai2, nilai3, rataRata, null});
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid, masukkan angka yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Custom cell renderer for displaying image in JTable cells
    class ImageCellRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText("");
            } else {
                super.setValue(value);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UTSCalculator::new);
    }
}