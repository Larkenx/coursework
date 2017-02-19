enum State { PRE, IN, POST };


class Frame {
    State state;
    BSTNode node;
    ...
}

stackwalk(... pre, ... in, ...post) {
    Stack<Frame> stack = new Stack<Frame>();
    stack.push(new Frame(State.PRE, root));
    while (! stack.empty()) {
        Frame curr = stack.pop();
        BSTNode n = curr.node;
        if (curr.state == PRE) {
            pre.accept(n.key);
            stack.push(new Frame(State.IN, n));
            if (n.left != null) {
                stack.push(new Frame(State.PRE, n.left));
            }
        } else if (curr.state == IN) {
            in.accept(n.key);
            stack.push(new Frame(State.POST, n));
            if (n.right != null) {
                stack.push(new Frame(State.PRE, n.right));
            }
        } else {
            post.accept(n.key);
        }
    }
}
