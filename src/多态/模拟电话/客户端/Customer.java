package ��̬.ģ��绰.�ͻ���;

import ��̬.ģ��绰.ϵͳ.CustomerService;
import ��̬.ģ��绰.ϵͳ.Language;

public class Customer {
    public static void main(String[] args) {
        CustomerService customerService=new CustomerService();// ʵ�����ͻ�������
        Language language=customerService.contact(66);// ͨ�����ŷ������ؽӿ�ʵ���ำֵ���ӿڶ���
        language.voice();// ����ӿ�ʵ�����ʵ�ַ���
    }
}
