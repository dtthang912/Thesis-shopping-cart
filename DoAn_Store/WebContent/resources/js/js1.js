function checkCheckbox() {
	var checkboxes = $("input:checkbox");
	var isChecked = false;
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			isChecked = true;
			break;
		}
	}
	if (!isChecked) {
		$(".message").html("Choose at least one row to perform!");
		$(".message").css("color", "red");
	}
	return isChecked;
}
function checkDelete() {
	if (checkCheckbox() && confirm("Are you sure?"))
		return true;
	return false;
}
jQuery(document).ready(function($) {
	$(".get-product").click(function() {
		window.location = $(this).data("href");
	});
});