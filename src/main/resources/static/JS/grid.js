const columnInfo = function () {
  this.title = "";
  this.name = "";
  this.template = "";
  this.dataType = "";
  this.width = "";
  this.align = "";
  this.hidden = false;
}
const createHeader = (headers) => {
  let str = "<thead><tr>";
  headers.forEach(header => {
    const width = header.width == "" ? "100px" : header.width + "px";
    const hidden = header.hidden ? "hidden" : "";
    let temp =  `<th ${hidden}>`;
        temp += `<div class="${header.name}" style="min-width: ${header.hidden ? "0px" : width}; height: 100%;">`;
        temp += `<span>${header.title}</span>`;
        temp += `</div>`;
        temp += `</th>`;
    str += temp;
  })
  str += "</tr></thead>";
  return str;
}

const createLayout = (headers, rows) => {
  const h = createHeader(headers);
  // const r = createRows(rows);
  const grid = document.getElementById("grid");
  grid.innerHTML = h;
}