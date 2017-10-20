Ext.define('Admin.view.profile.ProfileViewModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.profileViewModel',
	stores: {
		profileLists: {
    type: 'profileStore',//Store reference ==Store的属性 alias: 'store.assetsStore',		
    autoLoad: true //Auto load
}
}
});