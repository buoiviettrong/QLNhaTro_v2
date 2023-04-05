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
        temp += `<div class="${header.name}" style="min-width: ${header.hidden ? "0px" : width}; height: 100%; text-align: center">`;
        temp += `<span>${header.title}</span>`;
        temp += `</div>`;
        temp += `</th>`;
    str += temp;
  })
  str += "</tr></thead>";
  return str;
}
const createRows = (headers, rows) => {
  let str = "<tbody>";
  let k = 1;
  rows.forEach(item => {
    let row = `<tr>`;
    for(let i = 0, size = headers.length; i < size; i++) {
      const width = headers[i].width == "" ? "100px" : headers[i].width + "px";
      const hidden = headers[i].hidden ? "hidden" : "";
      let col = `<td ${hidden}>`
      col += `<div class="${headers[i].name}" style="min-width: ${headers[i].hidden ? "0px" : width}; height: 100%; text-align: ${headers[i].align}">`;
      if(headers[i].template != "") {
        let template = headers[i].template;
        let startPos = 0;
        let endPos = 0;
        while (true) {
          startPos = template.indexOf('{');
          endPos = template.indexOf('}');

          if(startPos == -1) break;

          const fieldName = template.slice(startPos + 1, endPos);
          const replaceStr = template.slice(startPos, endPos + 1);

          const strVal = item[fieldName];
          template = template.replace(replaceStr, strVal);
        }
        col += template;
      }
      else
        switch(headers[i].dataType) {
          case "Input" :
            col += `<input type="Text" id="${headers[i].name}_${item.id}" value="${item[headers[i].name]}" style="min-width: ${headers[i].hidden ? '0px' : width }; max-width: ${headers[i].hidden ? "0px" : width}; text-align: ${headers[i].align}" onchange="Change('${item['id']}')">`;
            break;
          case "Text" :
            col += `<span>${item[headers[i].name]}</span>`
            break;
          default :
            col += `<span>${item[headers[i].name]}</span>`
            break;
        }
      col += `</div></td>`;
      row += col;
    }
    row += "</tr>";
    str += row;
  })
  return str + "</tbody>";
}

const createLayout = (headers, rows) => {
  const h = createHeader(headers);
  const r = createRows(headers, rows);
  const grid = document.getElementById("grid");
  grid.innerHTML = h + r;
}