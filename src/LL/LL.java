package LL;

public class LL {
    private Node head;
    private Node tail;
    private int size;

    public LL() {
        head = null;
        tail = null;
        size = 0;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    private boolean detectLoopHelper(int size, Node head) {
        if (size == 1) {
            return head.next == this.head;
        }
        return detectLoopHelper(size - 1, head.next);
    }

    public boolean detectLoop() {
        return detectLoopHelper(this.size, head);
    }

    private Node reverseHelper(Node head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            Node newHead = reverseHelper(head.next);
            Node temp = newHead;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = head;
            head.next = null;
            return newHead;
        }
    }

    public void reverseLinkedList() {
        Node newHead = reverseHelper(head);
        while (newHead != null) {
            System.out.print(newHead.value + " ");
            newHead = newHead.next;
        }
    }

    public void insert(int element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public void insertAtStart(int element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void insertAtIndex(int index, int element) throws IndexOutOfBoundsException {
        Node newNode = new Node(element);
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            tail = tail.next;
        } else {
            int currentIndex = 0;
            Node temp = head;
            while (temp != null && currentIndex != (index - 1)) {
                temp = temp.next;
                currentIndex++;
            }
            if (temp == null) {
                throw new IndexOutOfBoundsException();
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            head = head.next;
        } else if (index == (size - 1)) {
            Node temp = head;
            while (temp.next != tail) {
                temp = temp.next;
            }
            temp.next = null;
        } else {
            Node temp = head;
            if (index > (size - 1)) {
                throw new IndexOutOfBoundsException();
            } else {
                int currentPosition = 0;
                while (temp != null && currentPosition != (index - 1)) {
                    temp = temp.next;
                    currentPosition++;
                }
                if (temp == null || temp.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                temp.next = temp.next.next;
            }
        }
        size--;
    }

    public Node mid() {
        if (head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        if (size % 2 == 0) {
            while (fast != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        } else {
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    public void print() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int size() {
        return this.size;
    }
}
