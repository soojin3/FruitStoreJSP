package kr.edu.mit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreMain {

	public static void main(String[] args) {
		// 1. �����԰�, 2. ����ľ�, 3. �Ǹ��ϱ�, 4. ����Ȯ��
		Scanner in = new Scanner(System.in);

		FruitStoreDAO dao = new FruitStoreDAOImpl();

		int menuNum;
		do {
			// �޴� ����ϱ�
			System.out.println("1. �����԰�");
			System.out.println("2. ����ľ�");
			System.out.println("3. �Ǹ��ϱ�");
			System.out.println("4. ����Ȯ��");
			System.out.print("�޴��� �������ּ���.(0�� ����): ");

			// ������Է¹ް� �ش�޴� �����ϱ�
			menuNum = in.nextInt();
			if (menuNum == 1) {
				String fname;
				int fprice;
				int fquantity;
				int fcode;
				System.out.println("�����Է±���Դϴ�.");
				// 1. ���ϸ�� �����ֱ�
				List<FruitVO> list = dao.listFruit();
				for (FruitVO vo : list) {
					System.out.println(vo);
				}
			
				System.out.println("��� �����Ϸ��� 1, ���� ����Ϸ��� 2");
				int ent = in.nextInt();
				FruitVO vo = new FruitVO();
				if (ent == 1) {					
					System.out.println("���� ������ �������Դϴ�.");
					System.out.println("���� �� �����ڵ带 �Է��� �ּ��� : ");
					fcode = in.nextInt();
					vo.setFruit_code(fcode);

					System.out.println("�߰��� ������ �Է��� �ּ���");
					fquantity = in.nextInt();
					vo.setQuantity(fquantity);
					
					dao.updateQuantityFruit(vo);
				} else if (ent == 2) {
					System.out.println("���ο� ������ �̸��� �Է��� �ּ���");
					fname = in.next();
					vo.setFruit_name(fname);
					
					System.out.println("������ �Է��� �ּ���");
					fprice =in.nextInt();
					vo.setPrice(fprice);
					
					System.out.println("������ �Է��� �ּ���");
					fquantity = in.nextInt();
					vo.setQuantity(fquantity);
					
					dao.insertFruit(vo);
					
				}else if(ent==3) {
					//�������� ���ư���
				}
				// 1. ���ϸ�� �����ֱ�
				// 2. �������� �߰����� �Է¹ް�
				// 3. ������ ���뿡 ���� �ʿ��� ���� �Է¹ް�
				// 3-1. �����ϰ��
				// �԰� ���� �ް� DBó��(������Ʈ)-> ��������� ���� =>�����ڵ�, ����
				// 3-2. �߰��� ���
				// �����̸�, ����, ���� �ް� DBó�� (insert)-> ��������� ���� =>�����̸�, ����, ����
				// 4. �Է¹��� ���� DB�� ó��
			} else if (menuNum == 2) {
				System.out.println("����ľǱ���Դϴ�.");

			} else if (menuNum == 3) {// �Ǹ��ϱ����Դϴ�
				// 1. ���ϸ�Ϻ����ֱ� -DB
				List<FruitVO> list = dao.listFruit();
				for (FruitVO vo : list) {
					System.out.println(vo);
				}
				System.out.println("���ϸ���Դϴ�. ���Ϲ�ȣ�� �������ּ��� : ");

				// 2. ����ڰ� ����(�ڵ�,����)
				int fruit_code = in.nextInt();// ���Ϲ�ȣ �Է�
				System.out.println("������ ������ �Է��� �ּ���");
				int quantity = in.nextInt();// ���ϼ���

				// 3. ���ұݾ׾ȳ� -(DB)���Ϻ� �Ѱ��� �˷��ֱ�
				FruitVO vo = new FruitVO();
				vo.setFruit_code(fruit_code);
				vo.setQuantity(quantity);
				System.out.println("�ѱ��űݾ��� " + dao.totalFruit(vo) + "�Դϴ�.");
				System.out.println("�����Ͻðڽ��ϱ�?(1: ����, 2: ���)");
				if (in.nextInt() == 1) {
					// 4. �ǸſϷ� -(DB)�Ǹ�ó��
					dao.insertSales(fruit_code, quantity);
				}

			} else if (menuNum == 4) {//����Ȯ��, �Ѹ���� �����ֱ�
				System.out.println("����Ȯ�α���Դϴ�.");
				//������ �����ֱ�
				List<SalesVO> list = dao.listSales();
				for (SalesVO vo  : list) {
					System.out.println(vo);
				}
				System.out.println("�Ѹ������ : "+dao.totalPrice()+ "�Դϴ�.");
				
			} else if (menuNum == 0) {
				System.out.println("�̿����ּż������մϴ�. ");
			}
		} while (menuNum != 0);

	}

}
