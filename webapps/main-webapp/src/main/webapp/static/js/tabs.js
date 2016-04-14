$(document).ready(function () {
    $("a.overlay-trigger[rel]").each(function(i) {
        $(this).overlay({
            // common configuration for each overlay
            oneInstance: false,
            closeOnClick: true,
            mask: {
            color: '#000',
            opacity: 0.5
            }
        });
    });
});

function closeOverlay() {
    $("a.overlay-trigger[rel]").each(function () {
        $(this).overlay().close();
    });
}
