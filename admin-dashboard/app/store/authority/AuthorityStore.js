Ext.define('Admin.store.authority.AuthorityStore', {
    extend: 'Ext.data.Store',
    alias: 'store.authorityStore',       //1.Store取别名（reference）
    model: 'Admin.model.authority.AuthorityModel',//2.设置model的全路径
  	proxy: {
		type: 'ajax',
		url: 'staff/findUserRole.json',
		params : { 
			roleLevel: loginUserRoleLevel
		}, 
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	//一共多少条记录的属性
		},
		simpleSortMode: true
	},

	pageSize: 10,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'userId'
    }
});

//假数据测试
// Ext.define('Admin.store.authority.AuthorityStore', {
//     extend: 'Ext.data.Store',
//     alias: 'store.authorityStore',       //1.Store取别名（reference）
//     model: 'Admin.model.authority.AuthorityModel',//2.设置model的全路径
//   proxy: {
//     //type: 'ajax',
//     type: 'memory',
//     //url: 'role/findPage.json',  //后台OrderController中的接口url地址
//     data:{
//     'items':[
//     { 'userId': 1 ,  'roleId' : 2 , 'userName': 'zjk', 'roleName': 'r1', "modulesText":"555-111-1224"  }
//     ]},
//     reader: {
//       type:'json', 
//       //rootProperty: 'content',    //结果集名字的属性
//       //totalProperty: 'totalElements'  //一共多少条记录的属性
//       root: 'items'
//     },
//     simpleSortMode: true  //简单排序模式
//   },
//   pageSize: 25,
//   autoLoad: true,
// 	remoteSort: true,//全局排序
//     sorters: {
//         direction: 'DESC',
//         property: 'userId'
//     }
// });