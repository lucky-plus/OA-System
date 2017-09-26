Ext.define('Admin.store.notice.NoticeStore', {
    extend: 'Ext.data.Store',
    alias: 'store.noticeStore',			  //1.Store取别名（reference）
    data:{
		'items':[
			{ 'noticeId': 1, 'noticeName': '文章标题',  "noticeTime":"2017-7-13",  "noticeAuthor":"Lisa"  },
			{ 'noticeId': 2, 'noticeName': '文章标题',  "noticeTime":"2017-7-13",  "noticeAuthor":"Lisa"  },
			{ 'noticeId': 3, 'noticeName': '文章标题',  "noticeTime":"2017-7-13",  "noticeAuthor":"Lisa"  },


		]
	},
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			root: 'items'
		}
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'id'
    }
});