document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const mountainName = params.get('mountain');
    const date = params.get('date');
    if (!mountainName || !date) {
      document.getElementById('result').innerHTML = '<p>Mountain name or date missing in URL</p>';
      return;
    }
  
    fetchHourlyForecast(mountainName, date);
  
    document.getElementById('back-button').onclick = () => {
      window.location.href = '/index.html';
    };
  });
  
  async function fetchHourlyForecast(mountainName, date) {
    try {
      const response = await fetch(`/meteo/${encodeURIComponent(mountainName)}/${encodeURIComponent(date)}`);
      if (!response.ok) throw new Error('Unable to load hourly forecast');
      const data = await response.json();
  
      document.getElementById('mountain-name').textContent = `${mountainName} - ${data.todaysDate}`;
  
        let html = `
        <table>
          <thead>
            <tr>
              <th>Time</th>
              <th>Temperature (°C)</th>
              <th>Relative Humidity (%)</th>
              <th>Apparent Temperature (°C)</th>
              <th>Rain (mm)</th>
              <th>Showers (mm)</th>
              <th>Snowfall (mm)</th>
              <th>Snow Depth (cm)</th>
              <th>Surface Pressure (hPa)</th>
              <th>Cloud Cover (%)</th>
              <th>Visibility (m)</th>
              <th>UV Index</th>
            </tr>
          </thead>
          <tbody>
            ${data.time.map((hour, i) => `
              <tr>
                <td>${hour}:00</td>
                <td>${data.temperature_2m[i]}</td>
                <td>${data.relative_humidity_2m[i]}</td>
                <td>${data.apparent_temperature[i]}</td>
                <td>${data.rain[i]}</td>
                <td>${data.showers[i]}</td>
                <td>${data.snowfall[i]}</td>
                <td>${data.snow_depth[i]}</td>
                <td>${data.surface_pressure[i]}</td>
                <td>${data.cloud_cover[i]}</td>
                <td>${data.visibility[i]}</td>
                <td>${data.uv_index[i]}</td>
              </tr>
            `).join('')}
          </tbody>
        </table>
      `;
      document.getElementById('result').innerHTML = html;
    } catch (error) {
      console.error(error);
      document.getElementById('result').innerHTML = '<p style="color:red;">Failed to load hourly forecast</p>';
    }
  }
  