package staff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class nhanvien extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nhanvien frame = new nhanvien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public nhanvien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Thông tin cá nhân");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 28;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 28;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 2;
		panel.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("3121560074");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 2;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_1_7 = new JLabel("địa chỉ:");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_7 = new GridBagConstraints();
		gbc_lblNewLabel_1_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_7.gridx = 5;
		gbc_lblNewLabel_1_7.gridy = 2;
		panel.add(lblNewLabel_1_7, gbc_lblNewLabel_1_7);
		
		JLabel lblNewLabel_2_5 = new JLabel("phú yên");
		lblNewLabel_2_5.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_5.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_5 = new GridBagConstraints();
		gbc_lblNewLabel_2_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_5.gridx = 6;
		gbc_lblNewLabel_2_5.gridy = 2;
		panel.add(lblNewLabel_2_5, gbc_lblNewLabel_2_5);
		
		JLabel lblNewLabel_1 = new JLabel("Tên :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cao Bảo Quỳnh");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1_8 = new JLabel("email:");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_8 = new GridBagConstraints();
		gbc_lblNewLabel_1_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_8.gridx = 5;
		gbc_lblNewLabel_1_8.gridy = 3;
		panel.add(lblNewLabel_1_8, gbc_lblNewLabel_1_8);
		
		JLabel lblNewLabel_2_6 = new JLabel("@@");
		lblNewLabel_2_6.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_6.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_6 = new GridBagConstraints();
		gbc_lblNewLabel_2_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_6.gridx = 6;
		gbc_lblNewLabel_2_6.gridy = 3;
		panel.add(lblNewLabel_2_6, gbc_lblNewLabel_2_6);
		
		JLabel lblNewLabel_1_2 = new JLabel("giới tính:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 2;
		gbc_lblNewLabel_1_2.gridy = 4;
		panel.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nam");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.gridwidth = 2;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 3;
		gbc_lblNewLabel_2_1.gridy = 4;
		panel.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_9 = new JLabel("ca làm việc:");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_9 = new GridBagConstraints();
		gbc_lblNewLabel_1_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_9.gridx = 5;
		gbc_lblNewLabel_1_9.gridy = 4;
		panel.add(lblNewLabel_1_9, gbc_lblNewLabel_1_9);
		
		JLabel lblNewLabel_2_7 = new JLabel("full time");
		lblNewLabel_2_7.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_7.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_7 = new GridBagConstraints();
		gbc_lblNewLabel_2_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_7.gridx = 6;
		gbc_lblNewLabel_2_7.gridy = 4;
		panel.add(lblNewLabel_2_7, gbc_lblNewLabel_2_7);
		
		JLabel lblNewLabel_1_3 = new JLabel("SDT:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_3 = new GridBagConstraints();
		gbc_lblNewLabel_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_3.gridx = 2;
		gbc_lblNewLabel_1_3.gridy = 5;
		panel.add(lblNewLabel_1_3, gbc_lblNewLabel_1_3);
		
		JLabel lblNewLabel_2_2 = new JLabel("0867922358");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_2.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_2.gridwidth = 2;
		gbc_lblNewLabel_2_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_2.gridx = 3;
		gbc_lblNewLabel_2_2.gridy = 5;
		panel.add(lblNewLabel_2_2, gbc_lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_11 = new JLabel("chức vụ:");
		lblNewLabel_1_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_11 = new GridBagConstraints();
		gbc_lblNewLabel_1_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_11.gridx = 5;
		gbc_lblNewLabel_1_11.gridy = 5;
		panel.add(lblNewLabel_1_11, gbc_lblNewLabel_1_11);
		
		JLabel lblNewLabel_2_8 = new JLabel("quản lí");
		lblNewLabel_2_8.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_8.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_8 = new GridBagConstraints();
		gbc_lblNewLabel_2_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_8.gridx = 6;
		gbc_lblNewLabel_2_8.gridy = 5;
		panel.add(lblNewLabel_2_8, gbc_lblNewLabel_2_8);
		
		JLabel lblNewLabel_1_4 = new JLabel("Ngày làm:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_4 = new GridBagConstraints();
		gbc_lblNewLabel_1_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_4.gridx = 2;
		gbc_lblNewLabel_1_4.gridy = 6;
		panel.add(lblNewLabel_1_4, gbc_lblNewLabel_1_4);
		
		JLabel lblNewLabel_2_3 = new JLabel("22");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_3.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_3 = new GridBagConstraints();
		gbc_lblNewLabel_2_3.gridwidth = 2;
		gbc_lblNewLabel_2_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_3.gridx = 3;
		gbc_lblNewLabel_2_3.gridy = 6;
		panel.add(lblNewLabel_2_3, gbc_lblNewLabel_2_3);
		
		JLabel lblNewLabel_1_10 = new JLabel("nơi làm việc:");
		lblNewLabel_1_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_10 = new GridBagConstraints();
		gbc_lblNewLabel_1_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_10.gridx = 5;
		gbc_lblNewLabel_1_10.gridy = 6;
		panel.add(lblNewLabel_1_10, gbc_lblNewLabel_1_10);
		
		JLabel lblNewLabel_2_9 = new JLabel("csc");
		lblNewLabel_2_9.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_9.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_9 = new GridBagConstraints();
		gbc_lblNewLabel_2_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_9.gridx = 6;
		gbc_lblNewLabel_2_9.gridy = 6;
		panel.add(lblNewLabel_2_9, gbc_lblNewLabel_2_9);
		
		JLabel lblNewLabel_1_5 = new JLabel("Ngày sinh :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_5 = new GridBagConstraints();
		gbc_lblNewLabel_1_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_5.gridx = 2;
		gbc_lblNewLabel_1_5.gridy = 7;
		panel.add(lblNewLabel_1_5, gbc_lblNewLabel_1_5);
		
		JLabel lblNewLabel_2_4 = new JLabel("18/04/2003");
		lblNewLabel_2_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_4.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_4 = new GridBagConstraints();
		gbc_lblNewLabel_2_4.gridwidth = 2;
		gbc_lblNewLabel_2_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_4.gridx = 3;
		gbc_lblNewLabel_2_4.gridy = 7;
		panel.add(lblNewLabel_2_4, gbc_lblNewLabel_2_4);
		
		JLabel lblNewLabel_1_6 = new JLabel("lương:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_6 = new GridBagConstraints();
		gbc_lblNewLabel_1_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_6.gridx = 5;
		gbc_lblNewLabel_1_6.gridy = 7;
		panel.add(lblNewLabel_1_6, gbc_lblNewLabel_1_6);
		
		JLabel lblNewLabel_2_10 = new JLabel("rất nhiều");
		lblNewLabel_2_10.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_10.setEnabled(true);
		GridBagConstraints gbc_lblNewLabel_2_10 = new GridBagConstraints();
		gbc_lblNewLabel_2_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_10.gridx = 6;
		gbc_lblNewLabel_2_10.gridy = 7;
		panel.add(lblNewLabel_2_10, gbc_lblNewLabel_2_10);
		
		JLabel lblNewLabel_1_13 = new JLabel("M Khẩu cũ:");
		lblNewLabel_1_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_13 = new GridBagConstraints();
		gbc_lblNewLabel_1_13.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_13.gridx = 4;
		gbc_lblNewLabel_1_13.gridy = 9;
		panel.add(lblNewLabel_1_13, gbc_lblNewLabel_1_13);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 9;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("đổi mật khẩu");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 9;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_1_12 = new JLabel("Mật khẩu mới:");
		lblNewLabel_1_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1_12 = new GridBagConstraints();
		gbc_lblNewLabel_1_12.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_12.gridx = 4;
		gbc_lblNewLabel_1_12.gridy = 10;
		panel.add(lblNewLabel_1_12, gbc_lblNewLabel_1_12);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 10;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Thông báo thành công");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 11;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
	}

}
