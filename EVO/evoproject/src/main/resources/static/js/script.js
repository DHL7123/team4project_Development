window.addEventListener('load', function() {
    var allElements = document.getElementsByTagName('*');
    Array.prototype.forEach.call(allElements, function(el) {
        var includePath = el.dataset.includePath;
        if (includePath) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    el.outerHTML = this.responseText;
                }
            };
            xhttp.open('GET', includePath, true);
            xhttp.send();
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {

    const menuItems = document.querySelectorAll('.menu-item');
    const menu = document.getElementById('menu');

    menuItems.forEach(function(item) {
        item.addEventListener('mouseenter', function() {
            console.log('Mouse entered item:', this.id);

            const itemId = this.id;
            //const menu = document.getElementById('menu');

            // itemId에 따라 배경 이미지 설정
            switch (itemId) {
                case 'all':
                    menu.style.backgroundColor = "white";
                    break;
                case 'chair':
                    menu.style.backgroundImage = "url('2.png')";
                    break;
                case 'lamp':
                    menu.style.backgroundImage = "url('3.png')";
                    break;
                case 'tsd':
                    menu.style.backgroundImage = "url('3.png')";
                    break;
                case 'cabinet':
                    menu.style.backgroundImage = "url('3.png')";
                    break;
                case 'decorative':
                    menu.style.backgroundImage = "url('3.png')";
                    break;
                case 'tableware':
                    menu.style.backgroundImage = "url('3.png')";
                    break;
                default:
                    menu.style.backgroundImage = "url('data:image/jpeg;base64,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')";
                    break;
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var searchIcon = document.getElementById("searchIcon");
    var modal = document.getElementById("modal");

    // 검색 아이콘 클릭 시 모달 열기
    searchIcon.addEventListener("click", function (event) {
        event.preventDefault(); // 링크이동 방지. index에 icon이 a태그로 감싸져 있음
        console.log("검색 아이콘 클릭됨");
        modal.style.display = "flex"; // 모달 보이기
    });

    // 검색 버튼 클릭 이벤트 처리
    var searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", function (event) {
        event.preventDefault(); // 기본 동작(페이지 이동 등) 방지
        console.log("검색 버튼 클릭됨");
        closeModal(); // 모달 닫기
    });

    // 모달 닫기 함수
    function closeModal() {
        var modal = document.getElementById("modal");
        console.log("모달 닫기");
        modal.style.display = "none";
    }

    // 모달 닫기 버튼 이벤트
    var closeButton = document.querySelector(".closeButton");
    closeButton.addEventListener("click", function () {
        console.log("닫기 버튼 클릭됨");
        closeModal();
    });
});

    window.addEventListener('load', function() {
    var allElements = document.getElementsByTagName('*');
    Array.prototype.forEach.call(allElements, function(el) {
    var includePath = el.dataset.includePath;
    if (includePath) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
    el.outerHTML = this.responseText;
}
};
    xhttp.open('GET', includePath, true);
    xhttp.send();
}
});
});



    function performSearch() {
    var searchInput = document.getElementById('searchInput').value;
    if (searchInput) {
    // 입력값을 포함한 URL로 리디렉션
    var url = '/product/search/' + encodeURIComponent(searchInput);
    window.location.href = url;
} else {
    alert('검색어를 입력하세요.');
}
}


function confirmDelete(button) {
    const boardTitle = button.getAttribute('data-board-title');
    const boardNo = button.getAttribute('data-board-no');
    if (confirm(boardTitle + "을(를) 삭제하시겠습니까?")) {
        window.location.href = "/admin/boards/delete/" + boardNo;
    }
}

// 문의 작성 시 이미지 크기 제한
document.addEventListener('DOMContentLoaded', function() {
    var createBoardImage = document.getElementById('createBoardImage');
    var editBoardImage = document.getElementById('editBoardImage');

    if (createBoardImage) {
        createBoardImage.addEventListener('change', function() {
            if (this.files.length > 0) {
                var fileSize = this.files[0].size / 1024 / 1024; // in MB
                if (fileSize > 5) {
                    alert('파일 크기가 너무 큽니다. 5MB 이하의 파일만 업로드 가능합니다.');
                    this.value = ''; // clear the input
                }
            }
        });
    }

    if (editBoardImage) {
        editBoardImage.addEventListener('change', function() {
            if (this.files.length > 0) {
                var fileSize = this.files[0].size / 1024 / 1024; // in MB
                if (fileSize > 5) {
                    alert('파일 크기가 너무 큽니다. 5MB 이하의 파일만 업로드 가능합니다.');
                    this.value = ''; // clear the input
                }
            }
        });
    }
});
