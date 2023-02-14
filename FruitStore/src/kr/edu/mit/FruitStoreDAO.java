package kr.edu.mit;

import java.util.ArrayList;
import java.util.List;

public interface FruitStoreDAO {
	
	//////SQL�������� ����//////
	
	//���ϵ��
	void insertFruit(FruitVO vo);
	//���ϸ�Ϻ����ֱ�
	ArrayList<FruitVO> listFruit();

	//����������Ʈ
	void updateQuantityFruit(FruitVO vo);
	
	//���Ϻ� �Ѱ��� �˷��ֱ�
	int totalFruit(FruitVO vo);
	
	
	//�Ǹ�ó��
	//1.�Ǹų��� �߰� -2.�Ǹų��� �߰� Ű�� �˾ƿ���- 3.�������̺� ����(�߰�)4.�������ó��
	//select last_insert_id()
	void insertSales(int fruit_code,int quantity);//Ű���� ����
	
	//���Ǹűݾ�
	long totalPrice();
	
	//���⳻������
	List<SalesVO> listSales();


}