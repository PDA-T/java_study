package ���Ͽ��;

import java.util.HashSet;
import java.util.Set;

public class Student implements Comparable<Student>{// ����ѧ����
    private String id;// ����˽�л�����
    private String name;// ����˽�л�����
    public Set<Course> courese;// ����Set��������
    public Student(String id,String name){// �������췽��
        this.id=id;// ��ֵid����
        this.name=name;// ��ֵname����
        this.courese=new HashSet<Course>();// �ӿ�ʵ����
    }
    public String getId(){// �������ȡ˽������
        return id;
    }
    public String getName(){// �������ȡ˽������
        return name;
    }
    /**
     * ��дhashCode()����
     */
    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime+result+((name==null)?0:name.hashCode());
        return result;
    }
    /**
     * ��дequals����ģ��
     */
    @Override// ��дע��
    public boolean equals(Object obj){// ��дequals����
        if (this==obj){// �ж�ֵ�Ƿ���ڴ���Ĳ���
            return true;
        }
        if (obj==null) {// �жϴ�������Ƿ�Ϊ��
            return false;
        }
        if (!(obj instanceof Student)){// ��������ߵĶ����Ƿ������ұߵ����ʵ��������boolean���͵����ݡ�
            return false;
        }
        Student student=(Student) obj;// ǿ��ת��
        /**
         * ���Ϊ����ж�,�¼������жϼ���
         */
        if (this.name==null){// �ж������Ƿ�Ϊ��
            if (student.name==null){// �ж�ǿת��������Ƿ�Ϊ��
                return true;
            }else {
                return false;
            }
        }else {
            if (this.name.equals(student.name)){// �ж������Ƿ����
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * Ĭ�ϱȽϹ���
     */
    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }
}
