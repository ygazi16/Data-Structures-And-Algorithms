package code;

import java.util.*;
import java.util.List;

import given.ContactInfo;
import given.DefaultComparator;
//Student Name : Yarkın GAZİ
/*
 * A phonebook class that should:
 * - Be efficiently (O(log n)) searchable by contact name
 * - Be efficiently (O(log n)) searchable by contact number
 * - Be searchable by e-mail (can be O(n)) 
 * 
 * The phonebook assumes that names and numbers will be unique
 * Extra exercise (not to be submitted): Think about how to remove this assumption
 * 
 * You need to use your own data structures to implement this
 * 
 * Hint: You need to implement a multi-map! 
 * 
 */
public class PhoneBook {

  /*
   * ADD MORE FIELDS IF NEEDED
   * 
   */

	BinarySearchTree<String, ContactInfo> named = new BinarySearchTree<String, ContactInfo>();
	BinarySearchTree<String, ContactInfo> numbered = new BinarySearchTree<String, ContactInfo>();
	
 	
  public PhoneBook() {
	named.setComparator(new DefaultComparator<String>());
	numbered.setComparator(new DefaultComparator<String>());
  }

  // Returns the number of contacts in the phone book
  public int size() {
    return named.size();
  }

  // Returns true is the phone book is empty, false otherwise
  public boolean isEmpty() {
    return numbered.isEmpty();
  }

//Adds a new contact overwrites an existing contact with the given info
  // Args should be given in the order of e-mail and address which is handled for
  // you
  // Note that it can also be empty. If you do not want to update a field pass
  // null
  public void addContact(String name, String number, String... args) {
    int numArgs = args.length;
    String email;
    String address;

    

    /*
     * Add stuff here if needed
     */

    if (numArgs > 0)
      if (args[0] != null)
        email = args[0];
    if (numArgs > 1)
      if (args[1] != null)
        address = args[1];
    if (numArgs > 2)
      System.out.println("Ignoring extra arguments");
   
  }

  // Return the contact info with the given name
  // Return null if it does not exists
  // Should be O(log n)!
  public ContactInfo searchByName(String name) {
    BinaryTreeNode<String, ContactInfo> info = named.getNode(name);
    if(info == null) {
    	return null;
    }
    ContactInfo value = info.getValue();
    return value;
    
  }

  // Return the contact info with the given phone number
  // Return null if it does not exists
  // Should be O(log n)!
  public ContactInfo searchByPhone(String phoneNumber) {
	 BinaryTreeNode<String, ContactInfo> info = numbered.getNode(phoneNumber); 
	 if(info == null) {
		 return null;
	 }
	 ContactInfo value = info.getValue();
	 return value;
  }

  // Return the contact info with the given e-mail
  // Return null if it does not exists
  // Can be O(n)
  public ContactInfo searchByEmail(String email) {
    List<BinaryTreeNode<String, ContactInfo>> emails = named.getNodesInOrder();
    ContactInfo info = null;
    for(BinaryTreeNode<String, ContactInfo> theNode : emails) {
    		if(theNode.getValue().getEmail() != null) {
	    		if(theNode.getValue().getEmail().equals(email)) {
	    			info = theNode.getValue();
	    		}
    		}
    }
    return info;
  }

   
  public boolean removeByName(String name) {
	BinaryTreeNode<String, ContactInfo> node = named.getNode(name);
	String nodeNum = node.getValue().getNumber();
    if(node.getValue() == null) {
    	return false;
    }
    numbered.remove(nodeNum);
    named.remove(name);
    return true;
  }

 
  public boolean removeByNumber(String phoneNumber) {
	  BinaryTreeNode<String, ContactInfo> node = numbered.getNode(phoneNumber);
	  String nodeName = node.getValue().getName();
	  if(node.getValue() == null) {
		  return false;
	  }
	  named.remove(nodeName);
	  numbered.remove(phoneNumber);
	  return true;
  }

  
  public String getNumber(String name) {
    ContactInfo contact = searchByName(name);
    return contact.getNumber();
  }

 
  public String getName(String number) {
	  ContactInfo contact = searchByPhone(number);
	  return contact.getName();
  }
  
 
  public boolean updateEmail(String name, String email) {
    ContactInfo contact = searchByEmail(email);
    if(contact == null) return false;
    contact.setEmail(email);
    return true;
  }
  
  
  public boolean updateAddress(String name, String address) {
	  ContactInfo contact = searchByName(name);
	    if(contact == null) return false;
	    contact.setAddress(address);
	    return true;
  }

  
  public List<ContactInfo> getContacts() {
    List<BinaryTreeNode<String, ContactInfo>> listing = named.getNodesInOrder();
    List<ContactInfo> contacts = new ArrayList<ContactInfo>();
    for(BinaryTreeNode<String, ContactInfo> node : listing) {
    		if(node.getValue() != null) {
    			contacts.add(node.getValue());
    		}
    }
    return contacts;
  }

  
  public void printContacts() {
    List<ContactInfo> listing = getContacts();
    for(ContactInfo infom : listing) {
    		System.out.println(infom);
    }
  }
}