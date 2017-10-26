Ext.define('Admin.store.personalNotes.PersonalNotesStore', {
    extend: 'Ext.data.Store',
    alias: 'store.personalNotesStore',			  //1.Store取别名（reference）
	model: 'Admin.model.personalNotes.PersonalNotesModel',
	proxy: {
		type: 'ajax',
		url: 'notes/findAll.json',
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	
		},
		simpleSortMode: true
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'notesId'
    }
});