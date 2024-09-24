<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Metronic | Pages - User Account</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link
	href="assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<link href="assets/admin/pages/css/profile.css" rel="stylesheet"
	type="text/css" />
<link href="assets/admin/pages/css/tasks.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/global/css/components.css"
	id="style_components" rel="stylesheet" type="text/css" />
<link href="assets/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="assets/admin/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link id="style_color"
	href="assets/admin/layout/css/themes/darkblue.css"
	rel="stylesheet" type="text/css" />
<link href="assets/admin/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>

<!-- #######################       BODY            ########################################### -->

<body
	class="page-header-fixed page-quick-sidebar-over-content page-style-square">
	
	<div>
		<%@ include file="/common/manager/header.jsp"%>
	</div>
	<div>
		<%@ include file="/view/profile_account/container.jsp"%>
	</div>
	<div>
		<%@ include file="/common/manager/footer.jsp"%>
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->

	<script src="assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script
		src="assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.sparkline.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script src="assets/admin/layout/scripts/quick-sidebar.js"
		type="text/javascript"></script>
	<script src="assets/admin/layout/scripts/demo.js"
		type="text/javascript"></script>
	<script src="assets/admin/pages/scripts/profile.js"
		type="text/javascript"></script>
	<script src="js/forgetpass.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			QuickSidebar.init(); // init quick sidebar
			Demo.init(); // init demo features
			Profile.init(); // init page demo
		});
	</script>
	<!-- END JAVASCRIPTS -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-37564768-1', 'keenthemes.com');
		ga('send', 'pageview');
	</script>
</body>

</html>