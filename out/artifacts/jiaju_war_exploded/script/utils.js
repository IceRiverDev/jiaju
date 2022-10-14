
function formChecker() {
    const stock = $("input[name='stock']").val()
    const sales = $("input[name='sales']").val()
    const price = $("input[name='price']").val()

    const numberRegex = new RegExp(/^\d+(\.\d+)?$/);

    if (!numberRegex.test(stock)) {
        return false
    }

    if (!numberRegex.test(sales)) {
        return false
    }

    if (!numberRegex.test(price)) {
        return false
    }

}

$("#updatefurn").click(formChecker)
$("#addfurn").click(formChecker)
