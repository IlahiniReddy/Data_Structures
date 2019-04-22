package searchengine.dictionary;

import java.util.ArrayList;

class Value
{
	String val;
	int count = 0;
	
	Value(String value, int count) 
	{
		this.val = value;
		this.count = count;
	}
	
	@Override
	public String toString() 
	{
		return "value="+ val + " count=" + count ;
	}
}

class Node
{
	String key;
	ArrayList<Value> val;
	Node next;
	
	Node()
	{
		val = new ArrayList<Value>();
	}
	Node(String k,String value)
	{
	   key = k;
	   val = new ArrayList<Value>();
	   val.add(new Value((String)value, 1));
	}
	
}
class LinkedList
{
	Node head;
	int size = 0;
	
	void insert(String key,String val)
	{
		if(head == null)
		{ 
			Node n = new Node(key,val);
			head = n;
			size++;
		}
		else
		{
			if(value(key) == null)
			{
				Node n = new Node(key,val);
				n.next = head;
				head = n;
				size++;
			}
			else
			{
				Node temp = head;
				while(temp != null)
				{
					int flag = 0;
					if(temp.key.equals(key))
					{   
						for(int i = 0;i < temp.val.size();i++)
						{
							if(temp.val.get(i).val.equals(val))
							{
								temp.val.get(i).count++;
								flag = 1;
							}
						}
						if(flag == 0)
						{
							temp.val.add(new Value((String)val,1));
						}
					}
					temp = temp.next;
				}
			}
		}
	}
	
	String[] keys()
	{
		Node temp = head;
		String k[] = new String[size];
		int i = 0;
		
		if(temp == null)
		{
			return k;
		}
		else
		{			
			while(temp != null)
			{
				k[i] = (String) temp.key;
				temp = temp.next;
				i++;
			}
			return k;
		}
	}
	
	ArrayList<Value> value(String k)
	{
		Node temp = head;
		if(head == null)
		{
			return null;
		}
		else
		{
			while(temp != null)
			{
				if(temp.key.equals(k))
				{
					return temp.val;
				}
				temp = temp.next;
			}
			return null;
		}
	}
	
	Node remove(String key)
	{
		if(head == null)
		{
			return  null;
		}
		else if(head.key.equals(key))
		{
			head = head.next;
			size--;
			return null;
		}
		else
		{
			Node temp = head;
			Node temp1 = head;
			while(temp != null)
			{
				if(temp.key.equals(key))
				{
					Node a = temp;
					temp1.next = temp.next;
					size--;
					return a;
				}
				temp1 = temp;
				temp = temp.next;
			}
			return null;
		}
	}
}

public class ListDictionary implements DictionaryInterface {

	LinkedList ll = new LinkedList();
	
	@Override
	public String[] getKeys() {
		return (String[]) ll.keys();
	}

	@Override
	public Object getValue(String str) {
		return ll.value(str);
	}

	@Override
	public void insert(String key, Object value) {
		ll.insert(key, (String) value);
	}

	@Override
	public void remove(String key) {
		ll.remove(key);
		
	}

}