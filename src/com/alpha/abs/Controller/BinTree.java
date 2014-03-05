/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.abs.controller;

import com.alpha.abs.model.BNode;
import com.alpha.abs.vo.BooksVO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dilanka_Nilan
 */
public class BinTree {

    BNode theBTRootNode;

    public BinTree() // constructor
    {
        theBTRootNode = null;
    }

    /**
     * *********************************************************************
     * Insert create by
     * *********************************************************************
     */
    protected BNode insertAB(BNode theRootNode, BNode myNewNode) {
        if (theRootNode == null) {
            theRootNode = myNewNode;
            //checks if the book name is smaller than 
            //the root object, if smaller appends to the left
        } else if (myNewNode.book.getBook_name().compareTo(theRootNode.book.getBook_name()) < 0) {
            theRootNode.leftBNode = insertAB(theRootNode.leftBNode, myNewNode);
        } else {
            // else if bigger appends to the right
            theRootNode.rightBNode = insertAB(theRootNode.rightBNode, myNewNode);
        }
        return theRootNode;
    }

    public void insertBST(BooksVO bookVO) {
        BNode newTNode = new BNode(bookVO);
        //calls insert above
        theBTRootNode = insertAB(theBTRootNode, newTNode);
    }

    /**
     * *********************************************************************
     * Delete
     * *********************************************************************
     */
    public void deleteMin() {

        theBTRootNode = deleteMin(theBTRootNode);

    }

    private BNode deleteMin(BNode x) {
        if (x.leftBNode == null) {
            return x.rightBNode;
        }
        x.leftBNode = deleteMin(x.leftBNode);

        return x;
    }

    public void deleteMax() {

        theBTRootNode = deleteMax(theBTRootNode);
    }

    private BNode deleteMax(BNode x) {
        if (x.rightBNode == null) {
            return x.leftBNode;
        }
        x.rightBNode = deleteMax(x.rightBNode);

        return x;
    }

    public void delete(String key) {
        theBTRootNode = delete(theBTRootNode, key);
    }

    private BNode delete(BNode x, String key) {
        if (x.leftBNode == null && x.rightBNode == null) {
            if ((key.compareTo(x.getBook().getISBN() + "")) == 0) {
                if (x.rightBNode == null) {
                    return x.leftBNode;
                }
                if (x.leftBNode == null) {
                    return x.rightBNode;
                }
                BNode t = x;

                x.rightBNode = deleteMin(t.rightBNode);
                x.leftBNode = t.leftBNode;
            }

        } else {
            if ((key.compareTo(x.getBook().getISBN() + "")) == 0) {
                if (x.rightBNode == null) {
                    return x.leftBNode;
                }
                if (x.leftBNode == null) {
                    return x.rightBNode;
                }
                BNode t = x;

                x.rightBNode = deleteMin(t.rightBNode);
                x.leftBNode = t.leftBNode;
            }
            if (x.leftBNode != null) {
                x.leftBNode = delete(x.leftBNode, key);
            }
            if (x.rightBNode != null) {
                x.rightBNode = delete(x.rightBNode, key);
            } else {
            }

        }
        return x;
    }

    // ------------------ findMinimum-------------------
    public BNode findMinimum(BNode root) {
        if (root == null) {
            return null;
        }

        if (root.leftBNode != null) {
            return findMinimum(root.leftBNode);
        }

        return root;
    }

    // ------------------ InOrder traversal-------------------
    protected void inorder(BNode theRootNode) {
        if (theRootNode != null) {
            inorder(theRootNode.leftBNode);
            theRootNode.show();
            inorder(theRootNode.rightBNode);
        }
    }

    //calls the method to do in order
    public void inorderBST() {
        inorder(theBTRootNode);
    }

    /**
     * *********************************************************************
     * Search
     * *********************************************************************
     */
    // ----- Search for key name and  returns ref. 
    //              to BNode or null if not found--------
    protected List<BooksVO> searchByType(BNode theRootNode, List all, String keyName) {
        //if the root is null returns null
        if (theRootNode.leftBNode == null && theRootNode.rightBNode == null) {

            if (keyName.compareTo(theRootNode.book.getType()) == 0) {
                all.add(theRootNode.getBook());

                //checks id the key is smaller than the current
                //record  if smaller traverses to the left
            }
            return all;
        } else {
            //checks if they are equal
            if (keyName.compareTo(theRootNode.book.getType()) == 0) {
                all.add(theRootNode.getBook());

            }
            if (theRootNode.leftBNode != null) {
                searchByType(theRootNode.leftBNode, all, keyName);
            }
            if (theRootNode.rightBNode != null) {
                // if bigger traverses to the left
                searchByType(theRootNode.rightBNode, all, keyName);
            }

        }
        return all;
    }

    protected List<BooksVO> searchByISBN(BNode theRootNode, List all, String keyName) {
        //if the root is null returns null
        if (theRootNode.leftBNode == null && theRootNode.rightBNode == null) {
            if (keyName.compareTo(theRootNode.book.getISBN() + "") == 0) {
                all.add(theRootNode.getBook());

            }
            return all;
        } else {
            //checks if they are equal
            if (keyName.compareTo(theRootNode.book.getISBN() + "") == 0) {
                all.add(theRootNode.getBook());

                //checks id the key is smaller than the current
                //record  if smaller traverses to the left
            }
            if (theRootNode.leftBNode != null) {
                searchByISBN(theRootNode.leftBNode, all, keyName);
            }
            if (theRootNode.rightBNode != null) {
                // if bigger traverses to the left
                searchByISBN(theRootNode.rightBNode, all, keyName);
            }
            return all;
        }
    }

    protected List<BooksVO> searchByBookName(BNode theRootNode, List all, String keyName) {
        //if the root is null returns 
        keyName = keyName.toLowerCase();
        if (theRootNode.leftBNode == null && theRootNode.rightBNode == null) {
            if (keyName.startsWith(theRootNode.book.getBook_name().toLowerCase())) {
                all.add(theRootNode.getBook());

            }
            return all;
        } else {
            //checks if they are equal
            if (keyName.startsWith(theRootNode.book.getBook_name().toLowerCase())) {
                all.add(theRootNode.getBook());

                //checks id the key is smaller than the current
                //record  if smaller traverses to the left
            }
            if (theRootNode.leftBNode != null) {
                searchByBookName(theRootNode.leftBNode, all, keyName);
            }
            if (theRootNode.rightBNode != null) {
                // if bigger traverses to the left
                searchByBookName(theRootNode.rightBNode, all, keyName);
            }
            return all;
        }
    }

    //returns null if no result else returns 
    //the BooksVO object matched with the keyName
    public List<BooksVO> getAllRecord() {
        List all = new LinkedList<BooksVO>();
        int size = getBTSSize(theBTRootNode);
        if (size == 1) {
            all.add(theBTRootNode.getBook());
            return all;
        } else {
            return getHelper(theBTRootNode, all);
        }
    }

    public List<BooksVO> getHelper(BNode current, List all) {

        if (current.leftBNode == null && current.rightBNode == null) {
            all.add(current.getBook());

        } else {
            if (current.leftBNode != null) {

                getHelper(current.leftBNode, all);
            }

            all.add(current.getBook());

            if (current.rightBNode != null) {

                getHelper(current.rightBNode, all);

            }

        }
        return all;

    }

    private int getBTSSize(BNode aNode) {
        int heightLeft = 0;
        int heightRight = 0;
        if (aNode.leftBNode != null) {
            heightLeft = getBTSSize(aNode.leftBNode);
        }
        if (aNode.rightBNode != null) {
            heightRight = getBTSSize(aNode.rightBNode);
        }
        if (heightLeft > heightRight) {
            return heightLeft + 1;
        } else {
            return heightRight + 1;
        }
    }

    public List<BooksVO> searchByType(String selectedItem) {
        List all = new LinkedList<BooksVO>();

        return searchByType(theBTRootNode, all, selectedItem);

    }

    public List<BooksVO> searchByISBN(String selectedItem) {
        List all = new LinkedList<BooksVO>();

        return searchByISBN(theBTRootNode, all, selectedItem);

    }

    public List<BooksVO> searchByBookname(String selectedItem) {
        List all = new LinkedList<BooksVO>();

        return searchByBookName(theBTRootNode, all, selectedItem);

    }

    public void deleteNode(String keyName) {
        delete(keyName);

    }
}
