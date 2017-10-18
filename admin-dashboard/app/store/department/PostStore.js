Ext.define('Admin.store.department.PostStore', {
    extend: 'Ext.data.Store',
    alias: 'store.postStore',			  //1.Store取别名（reference）
    model: 'Admin.model.department.PostModel',//2.设置model的全路径
	proxy: {
		type: 'ajax',
		url: 'post/findPostsByDeptId.json',	//后台OrderController中的接口url地址
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	//一共多少条记录的属性
		},
		simpleSortMode: true	//简单排序模式
	},
	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'postId'
    }
});
