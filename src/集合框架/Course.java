package ���Ͽ��;

public class Course {// �����γ���
    private String id;// ����˽�л�����
    private String name;// ����˽�л�����
    public Course(){// �������췽��
    }
    public Course(String id,String name){// �������췽��
        this.id=id;// ��ֵid����
        this.name=name;// ��ֵname����
    }
    public String getId(){// �������ȡ˽������
        return id;
    }
    public String getName(){// �������ȡ˽������
        return name;
    }
    public void setId(String id){// �������޸�˽������
        this.id=id;
    }
    public void setName(String name){// �������޸�˽������
        this.name=name;
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
        if (!(obj instanceof Course)){// ��������ߵĶ����Ƿ������ұߵ����ʵ��������boolean���͵����ݡ�
            return false;
        }
        Course course=(Course) obj;// ǿ��ת��
        /**
         * ���Ϊ����ж�,�¼������жϼ���
         */
        if (this.name==null){// �ж������Ƿ�Ϊ��
            if (course.name==null){// �ж�ǿת��������Ƿ�Ϊ��
                return true;
            }else {
                return false;
            }
        }else {
            if (this.name.equals(course.name)){// �ж������Ƿ����
                return true;
            }else{
                return false;
            }
        }
    }
}
