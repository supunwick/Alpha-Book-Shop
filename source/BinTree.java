public class BinTree {

    BNode theBTRootNode;

    public BinTree() // constructor
    {
        theBTRootNode = null;
    }

    /**
     * *********************************************************************
     * Insert this part was implemented by Dilanka Nilan
     * *********************************************************************
     */
    protected BNode insertAB(BNode theRootNode, BNode myNewNode) {
        if (theRootNode == null) {
            theRootNode = myNewNode;
            //checks if the book name is smaller than 
            //the root object, if smaller appends to the left
        } else if (myNewNode.book.getBook_name().compareTo(
                theRootNode.book.getBook_name()) < 0) {
            theRootNode.leftBNode = insertAB(theRootNode.leftBNode, myNewNode);
        } else {
            // else if bigger appends to the right
            theRootNode.rightBNode
                    = insertAB(theRootNode.rightBNode, myNewNode);
        }
        return theRootNode;
    }

    public void insertBST(BooksVO anyClass) {
        BNode anyClassBTNode = new BNode(anyClass);
        //calls insert above
        theBTRootNode = insertAB(theBTRootNode, anyClassBTNode);
    }

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
        //if the root is null returns null
        if (theRootNode.leftBNode == null && theRootNode.rightBNode == null) {
            if (keyName.compareTo(theRootNode.book.getISBN() + "") == 0) {
                all.add(theRootNode.getBook());

            }
            return all;
        } else {
            //checks if they are equal
            if (theRootNode.book.getBook_name().toLowerCase().contains(keyName.toLowerCase())) {
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
// This part was implemented by Supun Wickramasekara
    public BNode locateByTitle(String key, BNode node) {//pre order implementattion
        BNode result = null;
        if (node == null) {
            return null;
        }
        if (node.book.getBook_name().toLowerCase().equals(key.toLowerCase())) {
            return node;
        }
        if (node.leftBNode != null) {
            result = locateByTitle(key, node.leftBNode);
        }
        if (result == null) {
            result = locateByTitle(key, node.rightBNode);
        }
        return result;

    }
  
 // this part was implemented by Mahil Dilantha
    
    public BNode locateByISBN(int key, BNode node) {//pre order implementattion
        BNode result = null;
        if (node == null) {
            return null;
        }
        if (node.book.getISBN() == key) {
            return node;
        }
        if (node.leftBNode != null) {
            result = locateByISBN(key, node.leftBNode);
        }
        if (result == null) {
            result = locateByISBN(key, node.rightBNode);
        }
        return result;

    }
