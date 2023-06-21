/*
        二叉搜索树的插入操作
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode treeNode = new TreeNode(val);
            return treeNode;
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /*
        删除二叉搜索树中的节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val == key) {
            //1.左右孩子都为空
            if (root.left == null && root.right == null) {
                return null;
            }
            //2.左孩子为空，右孩子不为空
            if (root.left == null && root.right != null) {
                return root.right;
            }
            //3.左孩子不为空，右孩子为空
            if (root.left != null && root.right == null) {
                return root.left;
            }
            //4.左右孩子都不为空
            if (root.left != null && root.right != null) {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                root = root.right;
                return root;
            }
        }
        return root;
    }

    /*
        修剪二叉搜索树
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            // 寻找符合区间[low, high]的节点
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /*
        将有序数组转换为二叉搜索树(左闭右开)
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }

    /*
        把二叉搜索树转换成累加树
     */
    int count = 0;
    public TreeNode convertBST(TreeNode root) {
        //逆中序遍历
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        root.val += count;
        count = root.val;
        convertBST(root.left);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
