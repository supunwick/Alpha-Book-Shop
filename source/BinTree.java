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

